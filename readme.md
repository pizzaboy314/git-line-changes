utility for getting JSON data stats from a git log, over all time.
-----------

steps:
* in git bash, run `git log --numstat --format='%n%h%n%cn%n%ci%n%s%n' > stats.txt`
* copy the created file stats.txt to the same directory as this program and run it
