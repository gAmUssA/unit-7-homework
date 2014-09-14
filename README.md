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

AngularJS app specific steps:
1. Make sure you use RESTful API URL valid for your environment in app.ts file while
configuring RestangularProvider.
2. ProductController sends HTTP POST request to the place bid endpoint and
expects updated version of the ProductModel. It uses received product to update UI
bindings. If your logic differs from this one, make sure you update
ProductController’s placeBid() method accordingly.
3. If you implement WebSockets API, there is a dedicated BidService on the client
side. To use it uncomment bidService invocation in ProductController’s
placeBid() method and remove Restangular HTTP call.
4. Make sure you use URL valid for your environment inside BidService.ts file.

This app has two parts: JS and Java. The simplest way to deploy:
1. For JS part:
- Run “grunt build:dist”
- Manually copy the content from dist dir to the document root of your server.
2. For Java part:
- Create the war file with Gradle and deploy it under WildFly

If you prefer to automate the first step too, add the npm/Grunt tasks to the Gradle’s build script, for
example:
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
war.dependsOn npmBuild