import { Link } from 'react-router-dom'

import { useAuth } from '../auth/AuthProvider'

export default function Login() {
  const { authenticated, login } = useAuth()

  return (
    <main className="page-shell">
      <section className="auth-card">
        <div className="badge">Keycloak SSO</div>
        <h1>Ingreso con Keycloak</h1>
        <p className="subtitle">
          Este front no pide usuario y contraseña localmente. Te redirige al login oficial de
          Keycloak y luego recupera el JWT.
        </p>

        <button className="primary-action" type="button" onClick={() => void login()}>
          Entrar con Keycloak
        </button>

        <div className="card-note">
          <p>
            Cliente: <strong>sigma-frontend</strong>
          </p>
          <p>
            Realm: <strong>sigma-bb-realm</strong>
          </p>
        </div>

        {authenticated ? (
          <p className="success-note">
            Ya tienes una sesión activa. Puedes ir al <Link to="/dashboard">dashboard</Link>.
          </p>
        ) : null}
      </section>
    </main>
  )
}