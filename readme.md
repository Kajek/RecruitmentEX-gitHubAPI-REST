# Step 1
Clone the repository

# Step 2
Create a new branch, use your name as the branch name

# Step 3
Add required dependencies. Create a simple REST service that will return the Github repository's details. Details should
include:
* full name of the repository
* description of the repository
* git clone url
* information if it is forked
* number of forks
* date of creation (ISO format)

Please use https://developer.github.com/v3 as a backing API

The API of the service should look as follows:<br>
GET /repositories/{owner}/{repository-name}

Response:<br>

```yaml
{
  "fullName": "...",
  "description": "...",
  "cloneUrl": "...",
  "forked": "...",
  "forks": 0,
  "createdAt": "..."
}
```
# Step 4
Create 3 additional endpoints which will return:
* list of owner's repositories, having name matching provided regular expression
* the oldest repository
* most forked repository (if more than one, return the most recent)

# Step 5
Commit and push your changes to the branch

# Step 6
Create a Pull Request to the main branch
