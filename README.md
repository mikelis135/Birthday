# Birthday
App that shows a list of birthdays

## Stacks

- Kotlin
- Mvvm Architecture
- Repository pattern
- Room Database
- Retrofit Network

## Approaching the challenge

- My aim for this app is easy testability, clear structure, offline capability, easy pagination and scrolling, good user experience, if there's time will supoort dark mode
- I'll be importing libraries that will help achieve this goal and writing my code in that effect 

## How to run the app

- Clone or download the repo to your computer and open in anroid studio, then run and install on your physical device or emulator

## Destination

- An app that gets list of birthdays from the server in batches 
- As users scroll down it gets more birthdays, there are about 10,000 of those birthdays
- Birthdays are stored in the database locally, to support offline feature
- A solid pattern to allow creating of fake classes for easy testing is implemented
- A reactive model is used so changes react on the user interface


## Things left to do

- Adjust layout, remove tool bar to fit design
- Add onscroll listener to recycler view to listen for when user gets to the bottom of app to fetch next page of more data, better paginated cos it'
ll be faster and it'll increase gracefully, didn't import android paging library cos of its complexity
- create fakes for the contracts already written and unit test them, unit test the view model and network repository too, and use espresso for the ui test

## Thank you!

