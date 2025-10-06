# Authentication Service

## About
Authentication Service is used by Authentication/E-KYC Partners 
* to authenticate an individual's UIN/VID using one or more authentication types.
* to request E-KYC for an individual's UIN/VID using one or more authentication types.

## Authentication types
Any combination of the supported [authentication types](https://docs.mosip.io/1.2.0/id-authentication#authentication-types) may be used.
  
## Modalities
* Refer [biometric modalities](https://docs.mosip.io/1.2.0/biometrics#modalities).
* Above authentication types can be allowed/disallowed/mandated by the [configuration](../../docs/configuration.md#allowed-authentication-types) and the [Authentication/E-KYC Partner's Policy](../../docs/configuration.md).

## Partner/MISP validation
* Below partner/MISP data are validated before processing the authentication request:
  1. MISP License Key
  2. Partner ID
  3. Partner API Key

## Endpoints
### Authentication
```
POST /idauthentication/v1/auth/{MISP-LicenseKey}/{Auth-Partner-ID}/{Partner-Api-Key}
```

### E-KYC
```
POST /idauthentication/v1/kyc/{MISP-LicenseKey}/{Auth-Partner-ID}/{Partner-Api-Key}
```

### WebSub Callbacks
* Master data update callback — to process WebSub message sent from Master Data Service and clear master data cache, so that in next authentication the master data is re-cached.

---

## New Endpoints (Contributed by Obiechina)

### Health Details API

GET /api/v1/health/details


**Description:**  
Returns detailed service health and configuration information.

**Response Eg:**

{
  "status": "UP",
  "timestamp": "2025-10-06T09:00:00Z",
  "serviceName": "id-authentication-service",
  "version": "1.0.0",
  "environment": "dev",
  "configValue": "SAMPLE_CONFIG_VALUE"
}
```

**Expectation:**
- Returns 200 OK if the service is up.
- Returns `503 Service Unavailable` when simulated “DOWN” flag is set in configuration (`mosip.id.auth.simulateDown=true`).

---

# Audit/Event Logging API

POST /api/v1/audit/log

**Description:**  
Creates a new audit log .

 Example:
{
  "eventType": "LOGIN",
  "description": "User attempted login",
  "userId": "12345"
}


**Response :**

{
  "success": true,
  "eventId": "event1234",
  "timestamp": "2025-10-06T09:05:00Z"
}
```

Validation:
`eventType` and `userId` are mandatory fields.
 Returns `400 Bad Request` if validation fails.

DB:
- Events are stored in an H2 database




Git
1. Clone the repository:  https://github.com/obiechinac/id-authentication.git
   

2. Access Swagger/OpenAPI: http://localhost:8080/swagger-ui.html
   

Note: 
> Added by **Obiechina** as part of the MOSIP Senior Java Developer assessment task to  include:
1.  new modular endpoints for health reporting and
2. event logging with test coverage and documentation.


