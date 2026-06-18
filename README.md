RATE CS TEACHING STAFF

This is a SpringBoot CRUD application for rating Computer Science (CS) staff.

Users are able to view all existing staff ratings, view a single rating in detail, add a new rating, edit an existing rating, and delete a rating.

The app uses Java, Spring Boot, Thymeleaf, PostgreSQL, and server-side validation.

HOW TO RUN LOCALLY

1. Create a PostgreSQL database named "ratestaff".
2. Update src/main/resources/application.properties with your local database username and password.
3. Run the application using: .\mvnw spring-boot:run
4. Open the app in a browser: http://localhost:8080/ratings

HOW TO RUN TESTS

1. Run: .\mvnw test

AI DECLARATION:

I had no idea how to do the testing part of the assignment, so ChatGPT helped out a great deal with that.
I had trouble figuring out exactly how StaffRatingController.java worked, so I asked ChatGPT to give me the structure of the file and explain how the file worked (what each major section did), plus explaining Java syntax.
I had some errors/bugs in StaffRating.java that mainly consisted of spelling mistakes and incorrect formatting that I fixed using ChatGPT.
I used ChatGPT to figure out how to properly write form.html so that the drop-down menus and error messages worked properly