# Egg counter
<p>
<img alt="language" src="https://img.shields.io/badge/Language-Kotlin-blue"> 
</p>

## :egg: About
The egg counter counts the eggs quantity until the a value that was randomized is reached, and then an egg stack appears on the screen.

## 🏁 How to run:
1. Install Android Studio (if don't have it)
2. Clone the project from git
3. Open the project in Android Studio
4. Build and run the project

## :memo: How Works?
- Has 2 states: state 1 when in "one egg image" and state 2 when in "many eggs image"
- Increments one to the counter when the user touch on screen
- Show secret number when holds touch on screen
- Change image to many eggs when the counter reaches the secret number
- Returns to initial state when the user click 3 times on the many eggs image
- The app can store the data (counter and secret number)
- [BUG] If the app is in final state and the user close the app, when returns it will show the first state and then will enter in final state again.
