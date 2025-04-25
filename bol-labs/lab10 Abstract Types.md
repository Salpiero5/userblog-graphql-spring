# Lab 10 - Abstract Types

In this lab we will practice with the two abstract types: _interface_ and _union_.

---

## Exercise 1. Interfaces

In this exercise, we'll create one entrypoint for retrieving both blogs and comments. They'll need to have a common piece of data, so create an `IPublishable` interface with one `bool` property: `IsPublished`. Both `Blog` and `Comment` should implement this interface.

Now create your entrypoint, merge both items and adapt your query so that it can retrieve `Blog` and `Comment`-specific data.

<details>
<summary>Test: add some details for comments</summary>

```bash
query {
  items {
    published
    ... on Blog {
      title
      content
      creator {
        firstname
      }
    }
    ... on Comment {
      content
    }
  }
}
```

</details>

## Exercise 2. Unions

Add the next fields for the `Blog` type:

   | Fieldname          | Type    | Nullable |
   | ------------------ | ------- | -------- |
   | price              | Float   | yes      |
   | availableDate      | String  | yes      |
   | availableInCountry | Boolean | yes      |

Update your list of `Blog`s:
* first 2 blog have a price
* last 3 blogs do not have a price
* blog 3 has an `availableDate` somewhere in the future
* blog 4 and 5 are not available in the current country

For blogs 3, 4 and 5 we don't need to retrieve fields like `price`. Instead it would be beneficial to show relevant info in those cases. For example in which countries is the blog available?
Similarly, if a blog is available, it's not relevant to show fields for availability dates/countries.

<details>
<summary>Test!</summary>

```bash
query {
  blogById(id: 3) {   # 👈 test 1 - 5
    ... on Blog {
      title
      price
    }

    ... on NotAvailableYet {
      availableDate
    }

    ... on NotAvailableInCountry {
      availableInCountry
    }
  }
}
```

</details>