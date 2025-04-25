<<<<<<< HEAD
# Lab 8 - Integration Testing
=======
# Lab 9 - Integration Testing
>>>>>>> 27259b76beb95ead9b6167055a0fed847b59631b

As with most code, testing is important. Your GraphQL endpoints included. For testing to be successful, tests should be:

- **isolated** - tests don't affect each other
- **repeatable** - test results are always the same (if code doesn't change)
- **understandable** - just like regular code
- **structured** - just like regular code
- **easy** - make the hurdle as simple as possible for developers to write tests

**Integration test a few of your entrypoints**. A few interesting scenarios:

- testing the happy path - whether data is returned correctly with/without mock data
  - this will probably include implementing mocks with mock data
- testing for exceptions

Jest is the most popular testing package to use.

```sh
npm install --save-dev jest
npm init jest@latest
npx jest
```

**TypeScript?**

If you also want to use TypeScript in writing your tests, [you'll need a few more](https://jestjs.io/docs/getting-started#using-typescript).

```sh
npm install --save-dev babel-jest @babel/core @babel/preset-env @babel/preset-typescript @jest/globals
```

And a `babel.config.js` file:

```js
module.exports = {
  presets: [
    ['@babel/preset-env', {targets: {node: 'current'}}],
    '@babel/preset-typescript',
  ],
};
```

Then you should be error-free in defining your tests with TypeScript:

```ts
import {describe, expect, test} from '@jest/globals';

describe('sum module', () => {
  test('4 to be 4 and no TypeScript errors', () => {
    expect(4).toBe(4);
  });
});
<<<<<<< HEAD
```
=======
```
>>>>>>> 27259b76beb95ead9b6167055a0fed847b59631b
