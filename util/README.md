LastLogin
=========

A tool for creating lastlogin files.
------------------------------------

By default creates a dummy (but valid) lastlogin file in current directory, in a format accepted by Minecraft launcher, with blank username and password. Or you can specify your own username and password via command line arguments.

This fixes an annoying bug where in Mojang's official minecraft.jar launcher (as of 1.5.1) where it throws an ugly `java.io.FileNotFoundException` if the `lastlogin` file is missing from Minecraft data folder (`~/.minecraft` in Linux)


Usage
-----

- Compile: *(requires Java JDK, not the standard JRE)*

	javac ./LastLogin.java

- Run to generate a lastlogin file:

	java Lastlogin [username [password]]


LastLogin & Minecraft Installer
-------------------------------

`lastlogin` is a binary file. As such, an *HexDump* of its contents in plain text was generated using `xxd`:

	xxd -p ./lastlogin

This will output, for example, `b4135a0f49ea4b99`. To re-create the binary file, the hexdump is fed into `xxd`, using `-r` option to reverse the operation:

	xxd -p -r - ./lastlogin <<< b4135a0f49ea4b99

Which is exactly what the Minecraft Installer does to create a dummy `lastlogin` on-the-fly at install time. Since the hexdump was pre-generated, the user does not need Java JDK installed. It does not even need this class, or the `util/` dir for that matter. The sole purpose of `LastLogin.java` and this README is to explain where that "magic" `b4135a0f49ea4b99` in the `install` came from, or how you can change it if you want to customize the installer with a different username or password.

**ATTENTION:** Note that a `lastlogin` file alone, specially the default blank one, will *not* allow circumventing Mojang's Minecraft Authentication. Any username/password will still need to be verified by clicking the `Login` button, and the same rules about Offline/Demo mode applies. `lastlogin` is just a convenience, and its presence avoids a java exception, nothing else.
