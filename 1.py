f = open('1.bmp', 'rb')
f.seek(18)
bytes = f.read(8)
size = struct.unpack('<II', bytes)

print ('Image width: ' + str(size[0]))
print ('Image height: ' + str(size[1]))

raw_input("Enter any key")
	