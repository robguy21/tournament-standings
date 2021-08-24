# Usage
To write fixture results directly into the command remember to surround the entire list with a single set of quotation marks

# Java
This was compiled with ~~Coffee~~ Java version 16.0.2

Please make sure the output of java --version is at least 16.0.2

See [sdkman](https://sdkman.io/) for a nice way to manage different java versions

## Examples

### Output standings from stdin
**When using stdin wrap the fixture results in "quotation marks"

__cat an existing file__
```shell
java -jar /path/to/jarfile.jar "$(cat my-file.txt)"
```

__manually add fixture results from stdin__
```shell
java -jar /path/to/jarfile.jar "Team A 1, Team B 3
  Team A 1, Team C 2
  Team B 2, Team C 1"
```

__Pass in a path to a file to be read__
```shell
java -jar /path/to/jarfile.jar -f ./path/to/my/file.txt
```

__Need help?__
```shell
java -jar /path/to/jarfile.jar --help
```

# Limitations
All results are expected to be the following format:
```
<TEAM_NAME> <TOTAL>, <TEAM_NAME> <TOTAL>
<TEAM_NAME> <TOTAL>, <TEAM_NAME> <TOTAL>
```
_and so on_

# Missing Pieces
No repositories, may be nifty to store the tournament in memory so that the cli can be somewhat stateful

## Better exception handling

Need to implement exception handling for following cases
- File to import does not exist
- A line of the import is badly formatted and cannot be handled
- Multiple options are passed in (file and input)
- 

Along with this I don't have any Negative tests, those should be implemented alongside the above cases

## Expand on "League" concept
Currently, there is only a concept of a "League", my initial idea was to have Tournaments with different types 
(League / Single Elimination / etc) and then you could extend these base cases to a Tournament structure that makes use of different
tournament structures (ie - FIFA World Cup goes from Group Format to Single Elimination Format)

## Handle Tournament Rules more elegantly
Store the `PointRules` a bit more elegantly so that it is tied to the League (can be overridden for special occasions)-
ie - Premier League extends League but can override PointRules if they decided to award 5 points to winners instead of 3

## Concept: Event streaming
Ideally we would be able to get this data from some stream of events, this "tournament service" could then be used to integrate
all fixture events as they happen (Goal, Red card, Shot on target, Foul, Tri, Scrum, etc). If something like this were to be integrated,
I believe that this code base is structured in a way that it would be a kind migration to this type of a system.