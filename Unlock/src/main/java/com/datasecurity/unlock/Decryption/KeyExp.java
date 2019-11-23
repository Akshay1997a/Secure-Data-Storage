package com.datasecurity.unlock.Decryption;

class KeyExp {
    public static int[][] keyExpansion = new int[4][44];

    public void keyExpansion(int[][] key, int round) {
        SBoxes sBox = new SBoxes();

        int i;
        int j;
        int ans;
        int rconRow;
        int start = 0;

        // copy key to key expansion
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                keyExpansion[j][i] = key[j][i];
            }
        }

        // Key expansion algoritham
        while (round > 0) {
            start = start + 4;
            for (i = start; i < start + 4; i++) {
                for (j = 0; j < 4; j++) {
                    if (i == start) {
                        int[][] arrayB = new int[4][1];
                        copyArray(arrayB, start - 1);
                        rotWorld(arrayB);
                        subBytes(arrayB);
                        rconRow = (start / 4);
                        ans = arrayB[j][0] ^ keyExpansion[j][i - 4] ^ sBox.RCON[j][rconRow - 1];
                        keyExpansion[j][i] = ans;
                    } else {
                        ans = keyExpansion[j][i - 1] ^ keyExpansion[j][i - 4];
                        keyExpansion[j][i] = ans;
                    }
                }
            }
            round--;
        }
    }

    private void copyArray(int[][] arrayB, int row) {
        int i;
        for (i = 0; i < 4; i++) {
            arrayB[i][0] = keyExpansion[i][row];
        }
        for (int j = 0; i < 4; j++) {
            System.out.println(arrayB[j][0]);
        }
    }

    private void rotWorld(int[][] arrayB) {
        int i;
        int temp;
        temp = arrayB[0][0];
        for (i = 0; i < 3; i++) {
            arrayB[i][0] = arrayB[i + 1][0];
        }
        arrayB[i][0] = temp;
    }

    private void subBytes(int[][] arrayB) {
        AESUTIL util = new AESUTIL();
        SBoxes sBox = new SBoxes();

        int i = 0;
        String temp;
        String co_x, co_y;
        for (i = 0; i < 4; i++) {
            temp = util.hexToString(arrayB[i][0]);
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
            arrayB[i][0] = sBox.SBOX[x][y];
        }
    }

    public int[][] addRoundKey(int[][] state, int round) {
        int i, j;
        int ans;
        round = round * 4;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                ans = state[j][i] ^ keyExpansion[j][i + round];
                state[j][i] = ans;
            }
        }
        return state;
    }
}