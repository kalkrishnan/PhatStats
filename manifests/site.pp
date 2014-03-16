require nodejs
exec { "yeoman":
	provider => shell,
	command => "npm install -g yo"
}