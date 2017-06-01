//package com.epam.web.security;
//
//import epam.beans.RoleType;
//import epam.beans.User;
//import epam.repository.UserRepository;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import javax.transaction.Transactional;
//import java.util.HashSet;
//import java.util.Set;
//
//@Transactional
//public class DBUserDetails implements UserDetailsService
// {
//
//
//     private UserRepository userRepository;
//
//     public DBUserDetails(UserRepository userRepository){
//         this.userRepository=userRepository;
//     }
//     /**
//      * Locates the user based on the username. In the actual implementation, the search
//      * may possibly be case sensitive, or case insensitive depending on how the
//      * implementation instance is configured. In this case, the <code>UserDetails</code>
//      * object that comes back may have a username that is of a different case than what
//      * was actually requested..
//      *
//      * @param username the username identifying the user whose data is required.
//      *
//      * @return a fully populated user record (never <code>null</code>)
//      *
//      * @throws UsernameNotFoundException if the user could not be found or the user has no
//      *                                   GrantedAuthority
//      */
//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         try {
//             User user = userRepository.findByLogin(username);
//             if (user == null) {
//
//                 return null;
//             }
//
//             return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthorities(user));
//         }
//         catch (Exception e){
//             throw new UsernameNotFoundException("User not found");
//         }
//     }
//
//     private Set<GrantedAuthority> getAuthorities(User user){
//         Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
//
//             GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRoleType().toString());
//             authorities.add(grantedAuthority);
//
//         return authorities;
//     }
//
// }
