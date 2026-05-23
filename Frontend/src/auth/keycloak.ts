import Keycloak from 'keycloak-js'

const keycloak = new Keycloak({
    url: 'http://localhost:8080',
    realm: 'sigma-bb-realm',
    clientId: 'sigma-frontend',
})

export default keycloak