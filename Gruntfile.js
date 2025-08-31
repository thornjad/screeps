module.exports = function(grunt) {

    grunt.loadNpmTasks('grunt-screeps');

    grunt.initConfig({
        screeps: {
            options: {
                email: '<%= grunt.file.readJSON(".screeps.json").email %>',
                token: '<%= grunt.file.readJSON(".screeps.json").token %>',
                branch: 'default',
                // server: 'season' // uncomment for seasonal server
            },
            dist: {
                files: [{
                    expand: true,
                    cwd: 'dist/',
                    src: ['**/*.js'],
                    flatten: true
                }]
            }
        }
    });

};