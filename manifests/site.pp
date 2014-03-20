include nodejs
package { 'express':
  provider => npm,
  ensure => 'present',
  before  => package["yo"],
}
package { 'yo':
  ensure => present,
  provider => 'npm',
  require => Class["nodejs"],
}