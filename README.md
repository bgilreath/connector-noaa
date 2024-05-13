# NOAA National Weather Service Connector Example

A Camunda 8 example connector working with https://api.weather.gov/

## Build
You can package the Connector by running:

```bash
mvn clean package
```

This will create the following artifacts:

- A thin JAR without dependencies.
- An fat JAR containing all dependencies, potentially shaded to avoid classpath conflicts. This will not include the SDK artifacts since those are in scope `provided` and will be brought along by the respective Connector Runtime executing the Connector.

### Shading dependencies

You can use the `maven-shade-plugin` defined in the [Maven configuration](./pom.xml) to relocate common dependencies
that are used in other Connectors and the [Connector Runtime](https://github.com/camunda-community-hub/spring-zeebe/tree/master/connector-runtime#building-connector-runtime-bundles).
This helps to avoid classpath conflicts when the Connector is executed. 

Use the `relocations` configuration in the Maven Shade plugin to define the dependencies that should be shaded.
The [Maven Shade documentation](https://maven.apache.org/plugins/maven-shade-plugin/examples/class-relocation.html) 
provides more details on relocations.

## API

### Input

| Name     | Description      | Example           | Notes                                                                      |
|----------|------------------|-------------------|----------------------------------------------------------------------------|
| user_site  | your website or a unique looking tld    | `wx.x.com`           |  Used for NOAA API Authentication |
| user_email    | your email address | `made@up.com` | Used for NOAA API Authentication |
| grid_area    | NWS Forecast Office ID | `SGX` | limits the forecast grid |
| grid    | Forecast grid x,y coordinates | `35,57` | specific forecast area |

List of grid_area:
AKQ, ALY, BGM, BOX, BTV, BUF, CAE, CAR, CHS, CLE, CTP, GSP, GYX, ILM, ILN, LWX, MHX, OKX, PBZ, PHI, RAH, RLX, RNK, ABQ, AMA, BMX, BRO, CRP, EPZ, EWX, FFC, FWD, HGX, HUN, JAN, JAX, KEY, LCH, LIX, LUB, LZK, MAF, MEG, MFL, MLB, MOB, MRX, OHX, OUN, SHV, SJT, SJU, TAE, TBW, TSA, ABR, APX, ARX, BIS, BOU, CYS, DDC, DLH, DMX, DTX, DVN, EAX, FGF, FSD, GID, GJT, GLD, GRB, GRR, ICT, ILX, IND, IWX, JKL, LBF, LMK, LOT, LSX, MKX, MPX, MQT, OAX, PAH, PUB, RIW, SGF, TOP, UNR, BOI, BYZ, EKA, FGZ, GGW, HNX, LKN, LOX, MFR, MSO, MTR, OTX, PDT, PIH, PQR, PSR, REV, SEW, SGX, SLC, STO, TFX, TWC, VEF, AER, AFC, AFG, AJK, ALU, GUM, HPA, HFO, PPG, STU, NH1, NH2, ONA, ONP

### Output

Returns the wind speed in us units, i.e. "10 mph"

### Error codes

| Code | Description                                |
|------|--------------------------------------------|
| FAIL | returns errro message of errro caught      |

## Test locally

Run unit tests

```bash
mvn clean verify
```

### Test with local runtime

Use the [Camunda Connector Runtime](https://github.com/camunda-community-hub/spring-zeebe/tree/master/connector-runtime#building-connector-runtime-bundles) to run your function as a local Java application.

In your IDE you can also simply navigate to the `LocalContainerRuntime` class in test scope and run it via your IDE.
If necessary, you can adjust `application.properties` in test scope.

## Element Template

The element template for this sample connector is generated automatically based on the connector
input class using the [Element Template Generator](https://github.com/camunda/connectors/tree/main/element-template-generator/core).
The generation is embedded in the Maven build and can be triggered by running `mvn clean package`.

It is not mandatory to generate the element template for your connector and you can also create it manually.
However, the generator provides a convenient way to create the template and keep it in sync with the connector input class
and empowers you to prototype and iterate quickly.

The generated element template can be found in [element-templates/noaa-nws-connector.json](./element-templates/noaa-nws-connector.json).
