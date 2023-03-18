# Q-Lipa
Q-Lipa is an Android application built in Kotlin that allows passengers to settle their fare by scanning a QR code and completing M-Pesa payments. With Q-Lipa, users can quickly and securely pay for transportation services, making their daily commutes faster and more convenient.

This documentation will guide you through the general idea of the app.

## Development
### Installation
- Clone the repository: `https://github.com/Njoguu/Q-Lipa.git`
- Open the project in Android Studio
- Build and run the project on an Android device or emulator

## Usage
- Launch the Q-Lipa app on your Android device
- Enter your M-Pesa API credentials (Consumer Key and Secret) in the settings menu
- Scan the QR code provided by the driver or conductor
- Enter the fare amount and confirm the payment
- Wait for the payment confirmation message
- Show the confirmation message to the driver or conductor to complete the transaction

## Architecture
Q-Lipa follows the Model-View-ViewModel (MVVM) architecture pattern. The app consists of the following components:

- Model: The data model classes for the app
- View: The UI components for the app, including the activities, fragments, and layouts
- ViewModel: The view model classes that handle the business logic and communication between the model and view components

## API Integration
Q-Lipa uses the M-Pesa API to process payments

The API credentials (Consumer Key and Secret) are stored in the app's shared preferences. The credentials are encrypted using Android's KeyStore system to ensure that they are secure.

## QR Code Scanning
Q-Lipa uses [Code Scanner](https://github.com/yuriy-budiyev/code-scanner), a library for Android based on ZXing, to scan QR codes. The library provides a simple and easy-to-use interface for scanning QR codes.

## Contributing
Contributions to Q-Lipa are welcome. If you find a bug or have a feature request, please create an issue on GitHub. If you want to contribute code, please create a pull request with your changes.

## License
Q-Lipa is licensed under the MIT License. See [LICENSE](https://github.com/Njoguu/Q-Lipa/blob/main/LICENSE) for more information.



