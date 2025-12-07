# learnSpring app
* the lessons about spring boot



## LEARN 
* feature branches 
  * They are ordered from `feature/001-...` until `feature/022-readme`. And few more.
  * Every new one is small increment from previous one (new lesson).
  * Use Sourcetree or similar tool to find out what's added

* CHANGELOG.md file
  * Lessons descriptions
  
* CHANGELOG_DETAILS folder
  * What I don't like about SB is how easy your app can fail to start
  * So I put all that error (and successful) logs in the process down.


## RUN
* start docker
* ./startup-DB.bat (it will start the databases)
* ./startup-LS.bat (it will compile and start app up)
  * if you use IJ, this is the same: Run>Edit Configuration>Application>add new conf, without any special parameter

## TESTS
* Just run them. They use H2 in memory db. 