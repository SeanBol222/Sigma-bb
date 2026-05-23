import keycloak from '../auth/keycloak'

export function getAuthHeaders() {
  return keycloak.token
    ? {
        Authorization: `Bearer ${keycloak.token}`,
      }
    : {}
}

export async function apiFetch(input: RequestInfo | URL, init: RequestInit = {}) {
  const headers = new Headers(init.headers)

  if (keycloak.token) {
    headers.set('Authorization', `Bearer ${keycloak.token}`)
  }

  return fetch(input, {
    ...init,
    headers,
  })
}