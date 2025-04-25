# Lab 15 - N+1

Add a `console.log()` in the `getById()` method in your `BlogRepository`. If you then execute this query:

```gql
query {
  a: blogById(id: "b1") {
    title
  }
  b: blogById(id: "b1") {
    title
  }
  c: blogById(id: "b1") {
    title
  }
}
```

You'll notice `a`, `b` and `c` are all calling the repository, meaning we're also calling our data source 3 times.

Fix this by implementing a `DataLoader`. If all went well, your `console.log()` should only be called once.