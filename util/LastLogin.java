/*
LastLogin.java - A lastlogin file creator for Minecraft

	Copyright (C) 2013 Rodrigo Silva (MestreLion) <linux@rodrigosilva.com>

	This program is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	This program is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with this program. See <http://www.gnu.org/licenses/gpl.html>

By default creates a dummy (but valid) lastlogin file in current directory,
in a format accepted by Minecraft launcher, with 'user@example.com' username
and a blank password.

Or you can specify your own username and password via command line arguments.

This fixes an annoying bug where in Mojang's official minecraft.jar launcher
(as of 1.5.1) where it throws an ugly java.io.FileNotFoundException if the
lastlogin file is missing from Minecraft data folder (~/.minecraft in Linux)

Usage:
	javac LastLogin.java  &&                  # to compile, just needed once
	java LastLogin [username [password]] &&   # to generate lastlogin
	mv -i lastlogin ~/.minecraft              # to move it to minecraft dir
*/

import java.io.*;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.*;

public class LastLogin
{

public static String username = "user@example.com";
public static String password = "";

public static void main(String[] args) {
	try {
		File lastlogin = new File("lastlogin");

		if (args.length >= 1) username = args[0];
		if (args.length >= 2) password = args[1];

		Cipher cipher = getCipher();
		DataOutputStream dis;
		dis = new DataOutputStream(new CipherOutputStream(
			new FileOutputStream(lastlogin), cipher));
		dis.writeUTF(username);
		dis.writeUTF(password);
		dis.close();

		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		System.out.println("Created '" + lastlogin.getCanonicalPath() + "'");

	} catch (Exception e) {
		e.printStackTrace();
	}
}

private static Cipher getCipher() throws Exception {
	Random rand = new Random(43287234L);
	byte[] salt = new byte[8];
	rand.nextBytes(salt);
	PBEParameterSpec param = new PBEParameterSpec(salt, 5);

	SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES")
		.generateSecret(new PBEKeySpec("passwordfile".toCharArray()));
	Cipher cipher = Cipher.getInstance("PBEWithMD5AndDES");
	cipher.init(1, key, param);
	return cipher;
}

}
