# Alexa-End

## Database

You'll need a database to store all the relevant credentials. We are using a MySQL datbase running on port 3306. There is only on table necessary which is described in [codechella.sql](https://github.com/LorenzNickel/Codechella2020/blob/main/AlexaSkill/codechella.sql).

## Alexa-Skill "Frontend"

You will need to create an Alexa-Skill in the [alexa developer console](https://developer.amazon.com/alexa/console/ask). You can copy the interaction model we used for our skill from [interactionModel.json](https://github.com/LorenzNickel/Codechella2020/blob/main/AlexaSkill/interactionModel.json). You also have to activate account linking here:

![Account Linking](https://lorenznickel.de/filehost/1b7de534572f7c108bf48930ac54bac1.png)

We are using Amazon oAuth here, so you'll need to create a security profile at [the Amazon Console](https://developer.amazon.com/loginwithamazon/console/site/lwa/overview.html). Make sure to allow `https://pitangui.amazon.com/api/skill/link/M3RBCLU3VGEFWQ`, `https://alexa.amazon.co.jp/api/skill/link/M3RBCLU3VGEFWQ` & `https://layla.amazon.com/api/skill/link/M3RBCLU3VGEFWQ` as redirect URLs. The other setting should be like this:

Setting Name | Value
--- | ---
Your Web Authorization URI | `https://www.amazon.com/ap/oa`
Access Token URI | `https://api.amazon.com/auth/o2/token`
Your Client ID | *ID of your security profile*
Your Secret | *Secret of your security profile*
Your Authentication Scheme | `HTTP Basic (Recommended)`
Scope | `profile`

You also need to specify your `Endpoint` in the alexa developer console which in our case will be an AWS-lambda-function which we'll create later. It will start with something like `arn:aws:lambda:`.
Optionally you can also enable APL (Alexa Presentation Language) to create multimodal experiences by also making use of visuals on Alexa devices with a screen.

After you have set up everything here correctly (including the `Endpoint` which we'll cover later), don't forget to click `Build Model` which might take some seconds to complete.

## Alexa-Skill "Backend"

We'll host our skill using an AWS-lambda-function. As far as I know not all regions are supported by Amazon for hosting Alexa-Skills, so I'm hosting my Skills in Ireland which I know is supported. You'll need to create a new function and select `Java 8` as runtime. You also have to configure the `Alexa Skills Kit` as a trigger for this function:

![Alexa Skills Kit as trigger](https://lorenznickel.de/filehost/0b261c144f69e2592453cee8f9deedab.png)

It makes sense to specify the skillId which you can get from the console because that way the function is only invokable by your own skill and can not be abused by other invokers. In the top right corner you can also see now the `ARN` which is needed to complete the model in the alexa developer console as explained earlier.

### Compiling

As a next step we are going to package our code into a jar-file which we will deploy to the lambda-function. You can build the java-code of this repository by executing the following command in the [code folder](https://github.com/LorenzNickel/Codechella2020/tree/main/AlexaSkill/code): `mvn org.apache.maven.plugins:maven-assembly-plugin:2.6:assembly -DdescriptorId=jar-with-dependencies package`. Please upload the generated jar (with dependencies) to the AWS-lambda-function. Please also make sure to specify the correct handler for the lambda-function, in the case of this jar-file it should be `main.java.de.lorenznickel.ask.codechella.StreamHandler::handleRequest`.

### Environment Variables

The jar-file is depending on multiple dynamic inputs which have to be configured via environment variables. Please create the following environment variables for your lambda-function:

Key | Value
--- | ---
`consumerKey` | consumer-key of your Twitter app ("API key")
`consumerSecret` | consumer-secret of your Twitter app ("API secret key")
`databaseUrl` | `jdbc:mysql://`host`/`database-name
`password` | database-password
`skillId` | ID of your Alexa-Skill (can be obtained from the alexa developer console)
`username` | database-username

Please make sure to use the same Twitter app for the lambda function and for your webserver so that the access-token and access-token-secret from your database actually work with the specified consumer-key and consumer-secret.
