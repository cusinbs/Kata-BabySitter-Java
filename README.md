# Babysitter-Kata [Java]

## Kata Description
The original is taken from https://gist.github.com/jameskbride/5482722:

>Babysitter Kata
>
>Background
>----------
>This kata simulates a babysitter working and getting paid for one night.  The rules are pretty straight forward:
>
>The babysitter 
>- starts no earlier than 5:00PM
>- leaves no later than 4:00AM
>- gets paid $12/hour from start-time to bedtime
>- gets paid $8/hour from bedtime to midnight
>- gets paid $16/hour from midnight to end of job
>- gets paid for full hours (no fractional hours)
>
>Feature:
>As a babysitter
>In order to get paid for 1 night of work
>I want to calculate my nightly charge

## Approach
- This excercise follow SOLID and TDD. However, since I am still pretty much a beginner, there might be some unoptimized places in the code.
  Please feel free to let me know if you think I can improve on anything. I would really appreciate your feedbacks.
- Gathering information and write the test cases:
    1. A class for time operations:
        - Convert 12 hours format to 24 hours format: 
            There will be three input variable to get from the user (start time of the shift, bed time, and end time of the shift). However, 
            I figured user might use different time format like 12 hours format (8:00 PM) or 24 hours format (20:00). Therefore, I wrote a test
            class which includes the tests on converting 12 hours format to 24 hours format.
        - Check if the input time is in between the desired time range [inclusive]:
            Determining if the input is in a desired time range would be important to know what pay brackets the user is in. Therefore, I wrote
            some test cases for that function based on two scenarios:
              1. [min time] and [max time] might be in the same day (i.e: From 5 PM to 10 PM)
              2. [min time] and [max time] might be in different days (i.e: From 10 PM to 4 AM next morning)
        - Calculating the differece between two points of time would also needed to calculate the hours in different brackets. Also, when calculating
          time difference, there would be a problem when [startTime] and [endTime] are in different days. Therefore, I would add 24 to any time
          input that is in the next day to make it easier to calculate. 
            >For example, calculating time difference between 10PM and 2AM, 10 PM will be convert to 22 and 2AM will be convert to 26
            >26 - 22 = 4 hours.
    2. A class for baby sitter:
        - This class would consists of some constant variables and three input variables as I mentioned above (start shift time, end shift time,
          bed time).
        - Getters and setters for those 3 variables
        - A function to validate the user's input to determine whether the input is in 24 hours format using the functions from
          Time Operations Class. If not, convert it. Also, it would determine if the input time is in the desired time range [5 PM - 4 AM].
        - A function to calculate the nightly charge by making a call to the NightlyChargeCalculator class.
    3. A class to calculate nightly charge:
        - To follow the Dependency Inversion principle and Open-Closed principle in SOLID, I decdied to keep the class that calculate the nightly charge 
          separate.
        - Next, I wrote many test cases in different time brackets. The start time and end time can span in one pay-bracket or multiple different 
          pay-brackets.
  - Write the classes to pass the test case, then go back to change the modify and improve the test case repeatedly.
 
## Summary on how my solution works
  - Take three input from user (start time, end time, bed time)
  - Validate and convert those input (if neccessary) to 24 hours format.
  - Convert them to integers and calculate the time blocks in different brackets
  - Multiply those time blocks accordingly with the pay rates and add them up together to get the final result.
          
