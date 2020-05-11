function fn() {
  var env = karate.env;
  karate.log('karate.env system property was:', env);
  if (!env) {
    env = 'dev';
  }
  var config = {
    covidUrlBase: 'https://api.covid19api.com'
  };
  if (env == 'stage') {
    config.covidUrlBase = 'https://stage-covid-api';
  }
  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);
  return config;
}