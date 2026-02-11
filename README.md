1. Authentication Flow
   Login endpoint: POST /login (not protected)
   User sends credentials (username/password) as JSON
   AdminAuthenticationProcessingFilter intercepts the login request
   If credentials are valid, AdminAuthenticationSuccessHandler generates a JWT token
   Token is returned in the response
2. Authorization Flow
   Every subsequent request passes through MyAuthenticationFilter
   The filter extracts the JWT token from the Authorization header
   Token is validated and user information is loaded from the database
   Spring Security context is populated with user authorities/roles
   UrlFilterInvocationSecurityMetadataSource and UrlAccessDecisionManager handle URL-based permission checks