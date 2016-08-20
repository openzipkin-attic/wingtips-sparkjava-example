const {Tracer, BatchRecorder, ExplicitContext} = require('zipkin');
const {HttpLogger} = require('zipkin-transport-http');
const {restInterceptor} = require('zipkin-instrumentation-cujojs-rest');

const rest = require('rest');

const ctxImpl = new ExplicitContext();
const recorder = new BatchRecorder({
    logger: new HttpLogger({
        endpoint: 'http://localhost:9411/api/v1/spans'
    })
});

const tracer = new Tracer({ctxImpl, recorder});
const nameOfRemoteService = 'cujojs-client';
const client = rest.wrap(restInterceptor, {tracer, serviceName: nameOfRemoteService});

client({
    method: 'GET',
    path: 'http://localhost:9999/request'
}).then(function(response) {
    console.log('response: ', response);

    //TODO: DELETE THIS WHEN ISSUE IS RESOLVED - https://github.com/openzipkin/zipkin-js/issues/33
    setTimeout(function () {
        console.log('timeout');
    }, 5000);


});
