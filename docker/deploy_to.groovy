void call(app_env){
  stage "Deploy to ${app_env.long_name}", {
    node {
      unstash "workspace"

      //authenticate_to_marketplace()
      retag(env.GIT_SHA,app_env.short_name)
      sh "cat docker-compose.yml"
      def current_dir = sh( script: "pwd", returnStdout: true)
      sh """\
        cd ${app_env.ucp_bundle_directory}
        source ${app_env.ucp_bundle_directory}/env.sh
        cd ${current_dir}
        echo ======= running docker version to ensure connection to local docker client/server and swarm master and compose version
        docker-compose --version
        docker stack deploy -c docker-compose.yml sampleappstacks
      """
    }
  }
}
