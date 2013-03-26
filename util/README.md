LastLogin
=========

A tool for creating lastlogin files.
------------------------------------

By default creates a dummy (but valid) lastlogin file in current directory, in a format accepted by Minecraft launcher, with `user@example.com` username and a blank password. Or you can specify your own username and password via command line arguments.

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

This will output, for example, `6a2f1fa8c03c97e75a18e79fcc8626ff1c5e85e541515b88`. To re-create the binary file, the hexdump is fed into `xxd`, using `-r` option to reverse the operation:

	echo 6a2f1fa8c03c97e75a18e79fcc8626ff1c5e85e541515b88 | xxd -p -r - ./lastlogin

Which is exactly what the Minecraft Installer does to create a dummy `lastlogin` on-the-fly at install time. Since the hexdump was pre-generated, the user does not need Java JDK installed. It does not even need this class, or the `util/` dir for that matter. The sole purpose of `LastLogin.java` and this README is to explain where that "magic" `6a2f1fa8c03c97e75a18e79fcc8626ff1c5e85e541515b88` in the `install` came from, or how you can change it if you want to customize the installer with a different username or password.
