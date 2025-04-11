+-----------------------------------+
|           Presentation Layer      |
|  +----------------------------+   |
|  | :feature-auth             |   |
|  | +----------------------+  |   |
|  | | AuthViewModel        |  |   |
|  | | AuthFragment/Screen  |  |   |
|  | +----------------------+  |   |
|  | :feature-profile          |   |
|  | +----------------------+  |   |
|  | | ProfileViewModel     |  |   |
|  | | ProfileFragment      |  |   |
|  | +----------------------+  |   |
|  +----------------------------+   |
+-----------------------------------+
                ↓
+-----------------------------------+
|            Domain Layer           |
|  +----------------------------+   |
|  | :domain                   |   |
|  | +----------------------+  |   |
|  | | UserDomain           |  |   |
|  | | AuthRequest          |  |   |
|  | | UserRepository       |  |   |
|  | | AuthRepository       |  |   |
|  | +----------------------+  |   |
|  +----------------------------+   |
+-----------------------------------+
                ↓
+-----------------------------------+
|             Data Layer            |
|  +----------------------------+   |
|  | :core                     |   |
|  | +----------------------+  |   |
|  | | UserDto              |  |   |
|  | | AuthRequestDto       |  |   |
|  | | UserMapper           |  |   |
|  | | AuthMapper           |  |   |
|  | | UserService          |  |   |
|  | | AuthService          |  |   |
|  | | UserRepositoryImpl   |  |   |
|  | | AuthRepositoryImpl   |  |   |
|  | | UserUseCase          |  |   |
|  | | AuthUseCase          |  |   |
|  | +----------------------+  |   |
|  +----------------------------+   |
+-----------------------------------+
                ↓
+-----------------------------------+
|           Network / Storage       |
|  +----------------------------+   |
|  | Retrofit (API)            |   |
|  | Room (DB, опционально)    |   |
|  +----------------------------+   |
+-----------------------------------+


# Блок-схема архитектуры проекта

```mermaid
flowchart TD
    %% Presentation Layer
    A[Presentation Layer] --> B[:feature-auth]
    B --> B1[AuthViewModel]
    B --> B2[AuthFragment/Screen]
    A --> C[:feature-profile]
    C --> C1[ProfileViewModel]
    C --> C2[ProfileFragment]

    %% Domain Layer
    A --> D[Domain Layer]
    D --> E[:domain]
    E --> E1[contract]
    E1 --> E1a[UserRepository]
    E1 --> E1b[AuthRepository]
    E --> E2[model]
    E2 --> E2a[UserDomain]
    E2 --> E2b[UserRole]
    E2 --> E2c[AuthRequest]
    E2 --> E2d[AuthResponse]
    E2 --> E2e[RestorePassword]
    E2 --> E2f[ConfirmCode]
    E2 --> E2g[ResetPassword]
    E2 --> E2h[ResponseModel]
    E2 --> E2i[AuthorizationModel]
    E2 --> E2j[Party]
    E2 --> E2k[RequestState]
    E --> E3[navigation]
    E3 --> E3a[NavDestination]

    %% Data Layer
    D --> F[Data Layer]
    F --> G[:core]
    G --> G1[cases]
    G1 --> G1a[UserUseCase]
    G1 --> G1b[AuthUseCase]
    G --> G2[dao]
    G2 --> G2a[UserService]
    G2 --> G2b[AuthService]
    G --> G3[data]
    G3 --> G3a[dto]
    G3a --> G3a1[UserDto]
    G3a --> G3a2[AuthRequestDto]
    G3a --> G3a3[AuthResponseDto]
    G3a --> G3a4[RestorePasswordDto]
    G3a --> G3a5[ConfirmCodeDto]
    G3a --> G3a6[ResetPasswordDto]
    G3a --> G3a7[ResponseDto]
    G3 --> G3b[mapper]
    G3b --> G3b1[UserMapper]
    G3b --> G3b2[AuthMapper]
    G --> G4[repository]
    G4 --> G4a[UserRepositoryImpl]
    G4 --> G4b[AuthRepositoryImpl]
    G --> G5[di]
    G5 --> G5a[CoreModule]
    G5 --> G5b[NetworkModule]
    G5 --> G5c[RetrofitClient]

    %% Network/Storage
    F --> H[Network/Storage]
    H --> H1[Retrofit API]
    H --> H2[Room DB]