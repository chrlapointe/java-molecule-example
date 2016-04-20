module.exports = function(config) {

    config.set({
        basePath: '../../..',
        frameworks: ['mocha', 'chai'],

        files: [
            // this should be the path to your js code
            'src/main/webapp/assets/js/**/*.js',
            'src/test/js/**/*.js'
        ],

        reporters: ['progress', 'coverage'],

        preprocessors: {
            // this should be the path to your js code
            'src/main/webapp/assets/js/**/*.js': ['coverage']
        },

        // optionally, configure the reporter
        coverageReporter: {
            dir : 'build/reports/istanbul/',
            reporters: [
                { type: 'html', subdir: 'html' },
                { type: 'lcov', subdir: 'lcov' }
            ]
        },

        port: 9876,
        colors: true,
        autoWatch: true,
        singleRun: false,
        browsers: ['Firefox'],
        logLevel: config.LOG_ERROR
    });
};