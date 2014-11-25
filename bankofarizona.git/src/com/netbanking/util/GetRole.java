package com.netbanking.util;

import java.util.Iterator;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;

public class GetRole {

	
	public static String roles()
	{
		String role = "";
		
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		
		 Set<String> roles = AuthorityUtils.authorityListToSet(auth.getAuthorities());
		 
		 Iterator<String> itr=roles.iterator();
		 while(itr.hasNext())
		 {
			 role=itr.next();
			break; 
		 }
		    
		return role;
		
	}
}
