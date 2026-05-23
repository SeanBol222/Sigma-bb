import { createContext, useContext, useEffect, useMemo, useState, type ReactNode } from 'react'

import keycloak from './keycloak'

interface AuthContextType {
    authenticated: boolean
    initialized: boolean
    token?: string
    login: () => Promise<void>
    logout: () => Promise<void>
}

const AuthContext = createContext<AuthContextType | undefined>(undefined)

export function AuthProvider({ children }: { children: ReactNode }) {
    const [authenticated, setAuthenticated] = useState(false)
    const [initialized, setInitialized] = useState(false)
    const [token, setToken] = useState<string | undefined>(undefined)

    useEffect(() => {
        let active = true

        keycloak
            .init({
                onLoad: 'check-sso',
                pkceMethod: 'S256',
            })
            .then((isAuthenticated) => {
                if (!active) {
                    return
                }

                setAuthenticated(isAuthenticated)
                setToken(keycloak.token)
                setInitialized(true)
            })
            .catch(() => {
                if (!active) {
                    return
                }

                setAuthenticated(false)
                setToken(undefined)
                setInitialized(true)
            })

        const refreshInterval = window.setInterval(() => {
            if (!keycloak.authenticated) {
                return
            }

            keycloak.updateToken(30).catch(() => {
                void keycloak.logout({ redirectUri: `${window.location.origin}/login` })
            })
        }, 10000)

        keycloak.onAuthSuccess = () => {
            setAuthenticated(true)
            setToken(keycloak.token)
        }
        keycloak.onAuthRefreshSuccess = () => setToken(keycloak.token)
        keycloak.onAuthLogout = () => {
            setAuthenticated(false)
            setToken(undefined)
        }

        return () => {
            active = false
            window.clearInterval(refreshInterval)
            keycloak.onAuthSuccess = undefined
            keycloak.onAuthRefreshSuccess = undefined
            keycloak.onAuthLogout = undefined
        }
    }, [])

    const value = useMemo<AuthContextType>(
        () => ({
            authenticated,
            initialized,
            token,
            login: async () => {
                await keycloak.login({
                    redirectUri: `${window.location.origin}/dashboard`,
                })
            },
            logout: async () => {
                await keycloak.logout({
                    redirectUri: `${window.location.origin}/login`,
                })
            },
        }),
        [authenticated, initialized, token],
    )

    return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>
}

export function useAuth() {
    const context = useContext(AuthContext)

    if (!context) {
        throw new Error('useAuth must be used within AuthProvider')
    }

    return context
}