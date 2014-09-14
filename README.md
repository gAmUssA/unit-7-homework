unit-7-homework
===============

ModernWeb072714 unit 7 homework

Using the provided classes create the Java REST endpoint for receiving user’s bids on a
product in the auction in Java.
* Deploy it under WildFly server using IntelliJ IDEA.
* Integrate provided AngularJS Product page with this endpoint:
1. The user select the product and places a bid
2. The AngularJS app creates a JSON object Bid
3. The AngularJS app makes a Rest call to the endpoint implemented in WildFly
4. The Java code validates the received Bid, and if the price below the minimal price on
the Product, the bid is rejected. If the price is more than a reserved price, the user
receives congratulation on purchasing the product. In any other case the Bid is added to
the collection of Bids, which has to be re-sorted, and the top bid price and the top
bidder’s ID is returned to the front end.
5. The AngularJS app displays the Bid status top bid and top bidder on the Product page.
* Implement missing methods marked with TODO (getJsonObject() with javax.json API)

```java
task npmInstall(type: Exec) {
  inputs.files "package.json", "bower.json"
  outputs.dir "node_modules"
  commandLine 'npm', 'install'
}

task npmBuild(dependsOn: npmInstall, type: Exec) {
  inputs.file "Gruntfile.js"
  outputs.dir "dist"
  commandLine 'npm', 'run', 'build'
}
```
