import { useAuth } from '../auth/AuthProvider'

export default function Dashboard() {
  const { token, logout } = useAuth()

  return (
    <main className="page-shell">
      <section className="dashboard-card">
        <div className="dashboard-header">
          <div>
            <div className="badge">Dashboard protegido</div>
            <h1>JWT de Keycloak</h1>
          </div>

          <button className="secondary-action" type="button" onClick={() => void logout()}>
            Salir
          </button>
        </div>

        <p className="subtitle">
          El token de acceso recibido desde Keycloak se imprime completo abajo.
        </p>

        <section className="token-panel">
          <span>Access token</span>
          <pre>{token || 'Sin token disponible todavía'}</pre>
        </section>
      </section>
    </main>
  )
}