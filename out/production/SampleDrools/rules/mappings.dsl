[condition][]there is a user = $user:User
[condition][](who|the user) is one of "{userRoles}"=($user.type=="{userRoles}")
[condition][](who|the user) has name "{name}"=($user.name=="{name}")

[consequence][] user found =  System.out.println("User found using dslr is "+$user);
