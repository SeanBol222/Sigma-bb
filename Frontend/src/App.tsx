import { Navigate, Route, Routes } from 'react-router-dom'

import { PrivateRoute } from './auth/PrivateRoute'
import Dashboard from './pages/Dashboard'
import Login from './pages/Login'

export default function App() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/dashboard" replace />} />
      <Route path="/login" element={<Login />} />
      <Route
        path="/dashboard"
        element={
          <PrivateRoute>
            <Dashboard />
          </PrivateRoute>
        }
      />
      <Route path="*" element={<Navigate to="/dashboard" replace />} />
    </Routes>
  )
}