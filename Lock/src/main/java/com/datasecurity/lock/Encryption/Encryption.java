package com.datasecurity.lock.Encryption;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import com.datasecurity.lock.Utility.LockUtility;

public class Encryption {

    public boolean AES(String sourcePath, String destPath, String key) throws IOException {
        LockUtility lockUtility = new LockUtility();
        Encryption aes = new Encryption();
        AESUTIL util = new AESUTIL();

        FileInputStream fin = new FileInputStream(sourcePath);
        FileOutputStream fout = new FileOutputStream(destPath);

        String ext = lockUtility.getFileExtension(sourcePath) + "?";
        fout.write(ext.getBytes());

        byte[] byteKey = key.getBytes();
        byte[] input = new byte[16];
        byte[] output = new byte[16];
        int read;
        int flag = 0;

        while ((read = fin.read(input)) != -1) {
            if (read == 16) {
                output = aes.encrypt(input, byteKey, null, 16);
                fout.write(output);
            } else {
                flag = 1;
                int padLen = 16 - read;
                output = aes.encrypt(input, byteKey, util.padding(padLen), read);
                fout.write(output);
            }
        }

        if (flag == 0) {
            output = aes.encrypt(null, byteKey, util.padding(16), 0);
            fout.write(output);
        }

        fout.flush();
        fout.close();
        fin.close();
        return true;
    }

    public byte[] encrypt(byte[] plainText, byte[] key, byte[] padding, int len) {
        KeyExp keyE = new KeyExp();
        AESUTIL util = new AESUTIL();
        Encryption encrypt = new Encryption();

        int i = 0, j = 0, k = 0;
        int round = 10;

        int[][] state = new int[4][4];
        int[][] stateKey = new int[4][4];

        /// filling of state matrix with hex value
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                if (k < len) {
                    state[j][i] = util.charToIntHex(plainText[k]);
                } else {
                    state[j][i] = util.charToIntHex(padding[k - len]);
                }
                stateKey[j][i] = util.charToIntHex(key[k]);
                k++;
            }
        }

        // step 1:- Add round key
        keyE.keyExpansion(stateKey, round);
        state = keyE.addRoundKey(state, 0);

        i = 1;
        while (i < 10) {
            encrypt.subBytes(state);
            encrypt.shiftRows(state);
            state = encrypt.mixColumn(state);
            keyE.addRoundKey(state, i);
            i++;
        }
        encrypt.subBytes(state);
        encrypt.shiftRows(state);
        keyE.addRoundKey(state, 10);

        byte[] cypherText = util.getCypherByte(state);
        return cypherText;
    }

    private void subBytes(int[][] state) {
        AESUTIL util = new AESUTIL();
        SBoxes sBox = new SBoxes();

        int i = 0;
        int j = 0;
        String co_x, co_y;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                String temp = util.hexToString(state[i][j]);
                char[] coordinates = temp.toCharArray();
                if (coordinates.length == 1) {
                    co_x = "0";
                    co_y = Character.toString(coordinates[0]);
                } else {
                    co_x = Character.toString(coordinates[0]);
                    co_y = Character.toString(coordinates[1]);
                }
                int x = util.convertToHex(co_x);
                int y = util.convertToHex(co_y);
                state[i][j] = sBox.SBOX[x][y];
            }
        }
    }

    void shiftRows(int[][] state) {
        int i, j, k;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < i; j++) {
                int temp = state[i][0];
                for (k = 0; k < 3; k++) {
                    state[i][k] = state[i][k + 1];
                }
                state[i][k] = temp;
            }
        }
    }

    int[][] mixColumn(int[][] state) {
        SBoxes sBoxes = new SBoxes();
        AESUTIL util = new AESUTIL();

        int row, colm;
        int ans;
        int k = 0;
        int ansMat[][] = new int[4][4];

        for (k = 0; k < 4; k++) {
            for (row = 0; row < 4; row++) {
                ans = 0;
                for (colm = 0; colm < 4; colm++) {
                    ans = ans ^ util.multiplication(sBoxes.MATRIX[row][colm], state[colm][k]);
                }
                ansMat[row][k] = ans;
            }
        }
        return ansMat;
    }

    void addRoundKey(int[][] state, int[][] stateKey) {
        int i, j;
        int ans;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                ans = state[j][i] ^ stateKey[j][i];
                state[j][i] = ans;
            }
        }
    }
}