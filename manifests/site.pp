include nodejs
package { 'express':
  provider => npm,
  ensure => 'present',
  before  => Package["yo"],
}
package { ['yo','grunt-cli','bower']:
  ensure => present,
  provider => 'npm',
  require => Class["nodejs"],
}