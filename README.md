# Dog Image Application

The Dog Image Application is an Android app that utilizes the Dog Image SDK to fetch and display dog images. The app is built using Kotlin and leverages Dagger Hilt for dependency injection.

## Features

- Fetch and display a single dog image at start
- Fetch and display the next dog image
- Fetch and display the previous dog image
- Fetch and display a list of dog images

## Architecture Overview

The `DogsSdkApp` class is the main application class for your Android app. It extends the `Application` class and is annotated with `@HiltAndroidApp`, which indicates that Hilt should be used for dependency injection in this application.

### Components

1. **Application Class**: `DogsSdkApp` extends `Application`, which is the base class for maintaining global application state.

2. **Hilt Dependency Injection**: The `@HiltAndroidApp` annotation triggers Hilt's code generation, including a base class for your application that serves as the application-level dependency container.

3. **Use Cases**: The use cases (`GetFirstImage`, `GetNextImage`, `GetNNoOfImages`) encapsulate specific business logic and interact with the `ImageRepository`.

4. **Repository Pattern**: The `ImageRepository` interface abstracts the data layer, providing a clean API for data access.

5. **Model**: The `Image` class represents the data model.

The architecture follows the principles of Clean Architecture, separating concerns into different layers and promoting testability and maintainability.


## Installation

To set up the Dog Image Application, follow these steps:

1. **Clone the repository**:

    ```sh
    git clone <repository-url>
    cd <repository-directory>
    ```

2. **Open the project in Android Studio**.

3. **Build the project** to download the necessary dependencies.