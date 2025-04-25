# Lab 1 - Explore Github's GraphQL API

Next to the REST API, Github has implemented a GraphQL API.
Good documentation including reference and guides can be found at: https://docs.github.com/en/graphql

Github has also activated the GraphiQL interface and for safety reasons there is no way to alter data via the public GraphiQL instance.

---

## Exercise 1. Login

1. In a browser go to https://docs.github.com/en/graphql/overview/explorer

1. You'll need to be logged in

---

## Exercise 2. Root Query and Fields

1. In the left pane remove the commented lines

1. Run the default query:

   ```bash
   query {
     viewer {
       login
     }
   }
   ```

1. Look up in the Documentation Explorer which fields are supported by a _`User`_ by hovering over _`viewer`_ and then clicking _`User`_:

   <img src="img/github-api-hover-over-user.png" width="300">

   Tip: In the Document Explorer there are 2 sections: **`IMPLEMENTS`** and (a little lower) **`FIELDS`**.  
   You'll only need to look at the fields under **`FIELDS`**:

   <img src="img/github-api-fields.png" width="300">

1. Extend the query with a couple of fields you recognise. Pick scalar fields (single values). Their names are always in singular. Scalar fields are the 'leaves' in the graph.

    <details>
    <summary>Test!</summary>

   ```bash
   query {
     viewer {
       login
       bio
       company
     }
   }
   ```

    </details>

---

## Exercise 3. Arguments and Nested Objects

1. In the Document Explorer look up how to retrieve the details of a specific _user_.  
   Run a new query to retrieve the **`name`**, **`bio`** and **`email`** of the user named _'stamparm'_.

    <details>
    <summary>Test!</summary>

   ```bash
   query {
     user(login: "stamparm") {
       login
       bio
       company
     }
   }
   ```

   </details><br>

1. Extend the query so that the **`totalCount`** and **`totalDiskUsage`** of the user's **`repositories`** are shown as well.

   <details>
   <summary>Test!</summary>

   ```bash
   query {
     user(login: "stamparm") {
       login
       bio
       company
       repositories {
         totalCount
         totalDiskUsage
       }
     }
   }
   ```

   </details><br>

1. Replace the two totals from the previous query with the **`name`** of **`nodes`**, like so:

   (part of query)

    ```
    repositories {
      nodes {
        name
      }
    }
    ```

    <details>
    <summary>Test!</summary>

    ```bash
    query {
      user(login: "stamparm") {
        bio
        email
        name
        repositories {
          nodes {
            name
          }
        }
      }
    }
    ```

    </details>

   - An error is returned! Inspect the returned **`errors`** object.
     What do you think is the reason GitHub implemented this limitation?
   - Solve the problem by only retrieving the 3 most recent repositories.

      <details>
      <summary>Test!</summary>

      ```bash
      query {
        user(login: "stamparm") {
          bio
          email
          name
          repositories(last: 3) {
            nodes {
              name
            }
          }
        }
      }
      ```

      </details>

1. Extend the query to also retrieve the **`name`**, **`createdAt`**, **`description`** and **`forkCount`** of the repository named _'python-doh'_.

   <details>
   <summary>Test!</summary>

   ```bash
   query {
     user(login: "stamparm") {
       bio
       email
       name
       repositories(last: 3) {
         nodes {
           name
         }
       }
       repository(name: "python-doh") {
         name
         createdAt
         description
         forkCount
       }
     }
   }
   ```

   </details>

---

## Exercise 4. Variables

1. Remove the details of the repository named _'python-doh'_, but keep all other fields of user _'stamparm'_.

1. To reuse this query implement a variable **`$loginName`**.  
   Tip: In 3 spots something needs to be added or replaced.

   <details>
   <summary>Test!</summary>

   ```bash
   query ($loginName: String!) {
     user(login: $loginName) {
       bio
       email
       name
       repositories(last: 3) {
         nodes {
           name
         }
       }
     }
   }
   ```

   Query Variables:

   ```json
   {
     "loginName": "stamparm"
   }
   ```

   </details><br>

   Reuse the query by changing the value of **`loginName`** variable (your own login name).

---

## Exercise 5. Aliases and Fragments (if time permits)

1.  Two repositories of user _'stamparm'_ are _'python-doh'_ and _'python-ntlm'_.  
    Assume we want to compare these repositories on a web page.  
    Write a query which retrieves exactly this result:

    ```json
    {
      "data": {
        "user": {
          "name": "Miroslav Stampar",
          "DOH": {
            "createdAt": "2019-07-09T12:48:44Z",
            "description": "Python client for DNS over HTTPS (DoH) protocol"
          },
          "NTLM": {
            "createdAt": "2020-08-06T12:37:43Z",
            "description": "Automatically exported from code.google.com/p/python-ntlm"
          }
        }
      }
    }
    ```

    Tip: Try first to use aliases only, then further improve by using fragments.

    <details>
    <summary>Aliases</summary>

    ```bash
    query {
      user(login: "stamparm") {
      name
        DOH: repository(name: "python-doh") {
          createdAt
          description
        }
        NTLM: repository(name: "python-ntlm") {
          createdAt
          description
        }
      }
    }
    ```

    </details>

    <details>
    <summary>Aliases and Fragments</summary>

    ```bash
    query {
      user(login: "stamparm") {
      name
        DOH: repository(name: "python-doh") {
          ... pythonRepo
        }
        NTLM: repository(name: "python-ntlm") {
          ... pythonRepo
        }
      }
    }

    fragment pythonRepo on Repository {
      createdAt
      description
    }
    ```

    </details>