## RSA Encryption and Decryption Exercise

In this exercise, we implemented a basic version of the RSA algorithm to encrypt and decrypt numbers. Each team consisted of two members, with each member playing the roles of both Bob and Alice.

### Introduction

RSA (Rivest-Shamir-Adleman) is a widely used asymmetric cryptographic algorithm for secure communication over insecure channels. It involves generating public and private keys, where the public key is used for encryption, and the private key is used for decryption.

### How RSA Works

1. **Key Generation**: 
   - Each participant generates a pair of keys: a public key and a private key.
   - Public key consists of modulus \( n \) and public exponent \( e \).
   - Private key consists of modulus \( n \) and private exponent \( d \).
   - These keys are generated in such a way that certain mathematical properties hold true, allowing for encryption and decryption.

2. **Encryption**:
   - Alice wants to send a message to Bob. She encrypts the message using Bob's public key.
   - The message is represented as a number \( M \).
   - Alice calculates \( C = M^e \mod n \) and sends \( C \) to Bob.

3. **Decryption**:
   - Bob receives the ciphertext \( C \) from Alice.
   - He decrypts it using his private key.
   - Bob calculates \( M = C^d \mod n \) to obtain the original message.

### Eavesdropping and Trudy

In the last exercise, a third person named Trudy eavesdropped on the communication between Alice and Bob. Trudy intercepted the encrypted message \( C \) sent by Alice to Bob. Since Trudy doesn't have Bob's private key, she cannot directly decrypt the message. However, if Trudy manages to factorize the modulus \( n \) used in Bob's public key, she could compute Bob's private key and decrypt the message.

### Conclusion

The RSA algorithm provides a secure way of communication by using asymmetric encryption. However, it's vulnerable to attacks like eavesdropping if the private key or the modulus can be obtained by unauthorized parties. It's essential to keep the private key secure and choose strong parameters to mitigate such risks.