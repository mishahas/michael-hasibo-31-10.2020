## Read Me

All tests will be executed both on Chrome and Firefox in a headless configuration.
All drivers (for latest browesers) are included in the project.

_Note_: this project was created under MacOSX.<br/>
_Note_: All tests are defined in `/MyProject/src/test/java/WebAutomation/HomePageTest.java`

## Presequeties
- Java 1.8
- Selenium WebDriver
- TestNG
- Maven Package Manager
- WebDrivers for browsers (Included in this project)

## How to run
1. Install all the Presequeties
2. Download project from the repository
3. Run `mvn test`

## Tests Description
- `FooterPositiveSubmitValidation`: entering the data into the inputs and test the result
- `FooterNegativreSubmitValidation`: trying to submit without information and catch the error alerts
- `whatsappButtonValidation`: check functionality of button (validated new window)
- `arrowButtonsSwitchRadioButtonsValidation`: click the next image in the list and expect the elements to behave accordingly
- `popupValidation`: waits for the popup to appear (usually it waits around 30seconds, but may vary due to different logic by the website)
