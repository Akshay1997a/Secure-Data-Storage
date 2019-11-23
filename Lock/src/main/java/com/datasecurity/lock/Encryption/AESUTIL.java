package com.datasecurity.lock.Encryption;

class AESUTIL {

    public int multiplication(int mul, int mat) {
        MCTables table = new MCTables();
        int flag = hexToint(mul);
        char[] hex = hexToString(mat).toCharArray();

        String co_x, co_y;
        if (hex.length == 1) {
            co_x = "0";
            co_y = Character.toString(hex[0]);
        } else {
            co_x = Character.toString(hex[0]);
            co_y = Character.toString(hex[1]);
        }
        int x = convertToHex(co_x);
        int y = convertToHex(co_y);
        if (flag == 2) {
            return table.mc2[x][y];
        } else if (flag == 3) {
            return table.mc3[x][y];
        } else if (flag == 1) {
            return mat;
        }
        return 1;
    }

    String hexToString(int value) {
        String temp = Integer.toHexString(value);
        return temp;
    }

    int hexToint(int value) {
        String temp = hexToString(value);
        return Integer.parseInt(temp, 16);
    }

    int convertToHex(String value) {
        switch (value) {
        case "a":
            return 10;

        case "b":
            return 11;

        case "c":
            return 12;

        case "d":
            return 13;

        case "e":
            return 14;

        case "f":
            return 15;

        default:
            return Integer.parseInt(value);
        }
    }

    public int charToIntHex(byte ch) {
        String temp = String.format("%02x", ch);
        return Integer.parseInt(temp, 16);
    }

    public byte[] padding(int len) {
        byte[] padding = new byte[len];
        AESUTIL util = new AESUTIL();
        len = util.hexToint(len);
        for (int i = 0; i < len; i++) {
            padding[i] = (byte) len;
        }
        return padding;
    }

    public byte[] getCypherByte(int[][] state) {
        AESUTIL util = new AESUTIL();
        int k = 0;
        byte[] cypherText = new byte[16];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cypherText[k] = (byte) state[j][i];
                k++;
            }
        }

        return cypherText;
    }
}