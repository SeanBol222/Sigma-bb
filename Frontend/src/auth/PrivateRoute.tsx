import type { ReactNode } from 'react'
import { Navigate, useLocation } from 'react-router-dom'

import { useAuth } from './AuthProvider'

export function PrivateRoute({ children }: { children: ReactNode }) {
  const { authenticated, initialized } = useAuth()
  const location = useLocation()

  if (!initialized) {
    return <div className="screen-loader">Cargando sesión...</div>
  }

  if (!authenticated) {
    return <Navigate to="/login" replace state={{ from: location }} />
  }

  return <>{children}</>
}