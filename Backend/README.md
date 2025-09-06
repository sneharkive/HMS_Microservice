# Node command to generate a 512-bit secret key =>

``` bash
 node -e "console.log(require('crypto').randomBytes(64).toString('hex'))"
```