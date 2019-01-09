void call(app_env){
  stage "Deploy to ${app_env.long_name}", {
    //authenticate_to_marketplace()
    retag(env.GIT_SHA,app_env.short_name)
    ls
  }
}
