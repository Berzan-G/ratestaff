Tests were written for validation, persistence, and controller behaviour.

Validation tests:
1. invalid email is rejected
2. missing name is rejected
3. out-of-range score is rejected

Persistence tests:
1. saving and retrieving a rating works
2. deleting a rating removes it from the database

Controller tests:
1. GET /ratings returns successfully
2. valid POST create redirects
3. invalid POST create returns the form with errors

How to run tests:
.\mvnw test

Test database:
Tests use an H2 in-memory database through application-test.properties.