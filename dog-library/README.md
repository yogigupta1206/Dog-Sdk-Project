# Dog Image SDK

The Dog Image SDK is a Kotlin library that provides an easy way to fetch dog images. It uses Dagger Hilt for dependency injection to manage dependencies and ensure a clean architecture.

## Features

- Fetch a single dog image
- Fetch the next dog image
- Fetch the previous dog image
- Fetch a list of dog images

## Architecture
The SDK uses a clean architecture approach, which is evident from the separation of concerns and the use of different layers for different responsibilities. Here's a breakdown of the architecture:

### Domain Layer
- Contains the business logic and use cases.
- Example: `GetNextImage` use case in `src/main/java/com/yogigupta1206/dog_library/domain/use_cases/GetNextImage.kt`.

### Data Layer
- Manages data sources and repositories.
- Example: `ImageRepository` interface (not shown in the provided code but implied by its usage in the domain layer).

### Presentation Layer
- Manages the UI-related logic and interactions.
- Example: `ImageLibraryUseCases` in `src/main/java/com/yogigupta1206/dog_library/presentation/ImageLibraryUseCases.kt`.

### Dependency Injection
- Uses Dagger Hilt for dependency injection to manage dependencies across the application.
- Example: `DogImageSdkManager` in `src/main/java/com/yogigupta1206/dog_library/DogImageSdkManager.kt` which uses Hilt annotations.

### Testing
- Uses unit tests to verify the functionality of the SDK.
- Example: `DogImageSdkTest` in `src/test/java/com/yogigupta1206/dog_library/DogImageSdkTest.kt`.


## Installation

To use the Dog Image SDK in your project, follow these steps:

1. **Add the necessary dependencies** to your `build.gradle` file:

    ```gradle
    dependencies {
        implementation "com.google.dagger:hilt-android:<version>"
        kapt "com.google.dagger:hilt-compiler:<version>"
    }
    ```

2. **Apply the Hilt plugin** in your `build.gradle` file:

    ```gradle
    plugins {
        id 'kotlin-kapt'
        id 'dagger.hilt.android.plugin'
    }
    ```

3. **Initialize Hilt** in your `Application` class:

    ```kotlin
    @HiltAndroidApp
    class DogsSdkApp : Application()
    ```

## Usage

### Setting Up Dependency Injection

1. **Create a Dagger Hilt module** to provide the necessary dependencies:

    ```kotlin
    @Module
    @InstallIn(SingletonComponent::class)
    class RepoModule {

        @Provides
        @Singleton
        fun provideDogDataRepo(@ApplicationContext context: Context): DogDataRepo {
            return DogDataRepoImpl(DogDataSdkSource(DogImageSdkManager.create(context)))
        }
    }
    ```

### Fetching Dog Images

1. **Implement the `DogImageSdk` interface** to define the contract for fetching dog images:

    ```kotlin
    interface DogImageSdk {

        suspend fun getImage(): Image
        suspend fun getNextImage(): Image
        suspend fun getPreviousImage(): Image
        suspend fun getImages(n: Int): List<Image>
    }
    ```

2. **Use the `DogImageSdk` in your application**:

    ```kotlin
    @AndroidEntryPoint
    class MainActivity : AppCompatActivity() {

        @Inject
        lateinit var dogImageSdk: DogImageSdk

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            // Call the create method
            val sdk = DogImageSdkManager.create(applicationContext)

            // Use the sdk instance as needed
        }
    }
    ```

## Documentation

### `DogImageSdkManager`

`DogImageSdkManager` is responsible for managing the creation and provision of the `DogImageSdk` instance. It uses Dagger Hilt for dependency injection to provide the necessary dependencies.

#### Public Methods

- `create(context: Context): DogImageSdk`
    - Creates an instance of `DogImageSdk` using the provided application context.

### `DogImageSdk`

`DogImageSdk` is an interface that defines the contract for fetching dog images. It provides methods to get a single image, the next image, the previous image, and a list of images.

#### Public Methods

- `suspend fun getImage(): Image`
    - Fetches a single/first dog image.

- `suspend fun getNextImage(): Image`
    - Fetches the next dog image.

- `suspend fun getPreviousImage(): Image`
    - Fetches the previous dog image.

- `suspend fun getImages(n: Int): List<Image>`
    - Fetches a list of dog images.

