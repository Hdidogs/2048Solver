public class Cube {
    int valeur;
    boolean mergeDo;

    public static int multi = 2;
    public static int[] spawn = {2, 4};
    public static int nbrspawn = 1;

    public Cube(int val, boolean merge) {
        valeur = val;
        mergeDo = merge;
    }

    public int getValeur() {
        return valeur;
    }

    public boolean getMerge() {
        return mergeDo;
    }

    public static void printTable(Cube[][] grille) {
        for (int i = 0; i <= 3; i++) {
            System.out.println();

            for (int y = 0; y <= 3; y++) {
                System.out.printf("%4d" ,grille[i][y].getValeur());
            }
        }
        System.out.println("  ");
    }

    public static void ramdomCube(Cube[][] grille, int nbrspawn) {
        for (int i = 0; i < nbrspawn; i++) {
            boolean dispo = false;

            while (!dispo) {
                int x = (int) (Math.random() * (5 - 1));
                int y = (int) (Math.random() * (5 - 1));
                int ramdomSpawn = (int) (Math.random() * (10 - 1)) + 1;

                if (grille[x][y].getValeur() == 0) {
                    if (Score.getScore() >= 2048) {
                        if (ramdomSpawn <= 7) {
                            grille[x][y] = new Cube(spawn[1], false);
                        } else {
                            grille[x][y] = new Cube(spawn[0], false);
                        }
                    } else if (Score.getScore() >= 1024) {
                        if (ramdomSpawn <= 5) {
                            grille[x][y] = new Cube(spawn[1], false);
                        } else {
                            grille[x][y] = new Cube(spawn[0], false);
                        }
                    } else if (Score.getScore() >= 512) {
                        if (ramdomSpawn <= 4) {
                            grille[x][y] = new Cube(spawn[1], false);
                        } else {
                            grille[x][y] = new Cube(spawn[0], false);
                        }
                    } else if (Score.getScore() >= 216) {
                        if (ramdomSpawn <= 3) {
                            grille[x][y] = new Cube(spawn[1], false);
                        } else {
                            grille[x][y] = new Cube(spawn[0], false);
                        }
                    } else if (Score.getScore() >= 128) {
                        if (ramdomSpawn <= 2) {
                            grille[x][y] = new Cube(spawn[1], false);
                        } else {
                            grille[x][y] = new Cube(spawn[0], false);
                        }
                    } else {
                        if (ramdomSpawn <= 1) {
                            grille[x][y] = new Cube(spawn[1], false);
                        } else {
                            grille[x][y] = new Cube(spawn[0], false);
                        }
                    }
                    dispo = true;
                }
            }
        }
    }

    public static boolean mooveCubeUp(Cube[][] grille) {
        int ope = 0;

        for (int z = 0; z <= 3; z++) {
            for (int i = 0; i <= 3; i++) {
                for (int y = 0; y <= 3; y++) {
                    if (i == 0) {
                        grille[i][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                    } else if (grille[i-1][y].getValeur() == grille[i][y].getValeur() && grille[i-1][y].getValeur() > 0 && grille[i][y].getValeur() > 0) {
                        if (!grille[i-1][y].getMerge() && !grille[i][y].getMerge()) {
                            grille[i-1][y] = new Cube(grille[i][y].getValeur() * multi, true);
                            grille[i][y] = new Cube(0, false);
                            ope = ope + 1;
                        } else {
                            grille[i][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                        }
                    } else if (grille[i-1][y].getValeur() > grille[i][y].getValeur()) {
                        grille[i][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                    } else if (grille[i-1][y].getValeur() < grille[i][y].getValeur() && grille[i-1][y].getValeur() > 0) {
                        grille[i][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                    } else {
                        if (grille[i][y].getValeur() > 0) {
                            grille[i-1][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                            grille[i][y] = new Cube(0, false);
                            ope = ope + 1;
                        }
                    }
                    for (int a = 0; a <= 3; a++) {
                        for (int w = 0; w <= 3; w++) {
                            if (grille[a][w].getValeur() == 0) {
                                grille[a][w] = new Cube(grille[a][w].getValeur(), false);
                            }
                        }
                    }
                }
            }

        }

        for (int a = 0; a <= 3; a++) {
            for (int w = 0; w <= 3; w++) {
                grille[a][w] = new Cube(grille[a][w].getValeur(), false);
            }
        }

        Score.setScore(Cube.countVal(grille));

        if (ope >= 1) {
            ramdomCube(grille, nbrspawn);
            Score.setScore(Cube.countVal(grille));

            return false;
        } else {
            return true;
        }
    }

    public static boolean mooveCubeDown(Cube[][] grille) {
        int ope = 0;

        for (int z = 0; z <= 3; z++) {
            for (int i = 3; i >= 0; i--) {
                for (int y = 0; y <= 3; y++) {
                    if (i == 3) {
                        grille[i][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                    } else if (grille[i+1][y].getValeur() == grille[i][y].getValeur() && grille[i+1][y].getValeur() > 0 && grille[i][y].getValeur() > 0) {
                        if (!grille[i+1][y].getMerge() && !grille[i][y].getMerge()) {
                            grille[i+1][y] = new Cube(grille[i][y].getValeur() * multi, true);
                            grille[i][y] = new Cube(0, false);
                            ope = ope + 1;
                        } else {
                            grille[i][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                        }
                    } else if (grille[i+1][y].getValeur() > grille[i][y].getValeur()) {
                        grille[i][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                    } else if (grille[i+1][y].getValeur() < grille[i][y].getValeur() && grille[i+1][y].getValeur() > 0) {
                        grille[i][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                    } else {
                        if (grille[i][y].getValeur() > 0) {
                            grille[i+1][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                            grille[i][y] = new Cube(0, false);
                            ope = ope + 1;
                        }
                    }
                    for (int a = 0; a <= 3; a++) {
                        for (int w = 0; w <= 3; w++) {
                            if (grille[a][w].getValeur() == 0) {
                                grille[a][w] = new Cube(grille[a][w].getValeur(), false);
                            }
                        }
                    }
                }
            }

        }

        for (int a = 0; a <= 3; a++) {
            for (int w = 0; w <= 3; w++) {
                grille[a][w] = new Cube(grille[a][w].getValeur(), false);
            }
        }

        Score.setScore(Cube.countVal(grille));

        if (ope >= 1) {
            ramdomCube(grille, nbrspawn);
            Score.setScore(Cube.countVal(grille));

            return false;
        } else {
            return true;
        }
    }

    public static boolean mooveCubeLeft(Cube[][] grille) {
        int ope = 0;

        for (int z = 0; z <= 3; z++) {
            for (int i = 0; i <= 3; i++) {
                for (int y = 0; y <= 3; y++) {
                    if (y == 0) {
                        grille[i][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                    } else if (grille[i][y-1].getValeur() == grille[i][y].getValeur() && grille[i][y-1].getValeur() > 0 && grille[i][y].getValeur() > 0) {
                        if (!grille[i][y-1].getMerge() && !grille[i][y].getMerge()) {
                            grille[i][y-1] = new Cube(grille[i][y].getValeur() * multi, true);
                            grille[i][y] = new Cube(0, false);
                            ope = ope + 1;
                        } else {
                            grille[i][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                        }
                    } else if (grille[i][y-1].getValeur() > grille[i][y].getValeur()) {
                        grille[i][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                    } else if (grille[i][y-1].getValeur() < grille[i][y].getValeur() && grille[i][y-1].getValeur() > 0) {
                        grille[i][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                    } else {
                        if (grille[i][y].getValeur() > 0) {
                            grille[i][y-1] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                            grille[i][y] = new Cube(0, false);
                            ope = ope + 1;
                        }
                    }
                    for (int a = 0; a <= 3; a++) {
                        for (int w = 0; w <= 3; w++) {
                            if (grille[a][w].getValeur() == 0) {
                                grille[a][w] = new Cube(grille[a][w].getValeur(), false);
                            }
                        }
                    }
                }
            }
        }

        for (int a = 0; a <= 3; a++) {
            for (int w = 0; w <= 3; w++) {
                grille[a][w] = new Cube(grille[a][w].getValeur(), false);
            }
        }

        Score.setScore(Cube.countVal(grille));

        if (ope >= 1) {
            ramdomCube(grille, nbrspawn);
            Score.setScore(Cube.countVal(grille));

            return false;
        } else {
            return true;
        }
    }

    public static boolean mooveCubeRight(Cube[][] grille) {
        int ope = 0;

        for (int z = 0; z <= 3; z++) {
            for (int i = 0; i <= 3; i++) {
                for (int y = 3; y >= 0; y--) {
                    if (y == 3) {
                        grille[i][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                    } else if (grille[i][y+1].getValeur() == grille[i][y].getValeur() && grille[i][y+1].getValeur() > 0 && grille[i][y].getValeur() > 0) {
                        if (!grille[i][y+1].getMerge() && !grille[i][y].getMerge()) {
                            grille[i][y+1] = new Cube(grille[i][y].getValeur() * multi, true);
                            grille[i][y] = new Cube(0, false);
                            ope = ope + 1;
                        } else {
                            grille[i][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                        }
                    } else if (grille[i][y+1].getValeur() > grille[i][y].getValeur()) {
                        grille[i][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                    } else if (grille[i][y+1].getValeur() < grille[i][y].getValeur() && grille[i][y+1].getValeur() > 0) {
                        grille[i][y] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                    } else {
                        if (grille[i][y].getValeur() > 0) {
                            grille[i][y+1] = new Cube(grille[i][y].getValeur(), grille[i][y].getMerge());
                            grille[i][y] = new Cube(0, false);
                            ope = ope + 1;
                        }
                    }
                    for (int a = 0; a <= 3; a++) {
                        for (int w = 0; w <= 3; w++) {
                            if (grille[a][w].getValeur() == 0) {
                                grille[a][w] = new Cube(grille[a][w].getValeur(), false);
                            }
                        }
                    }
                }
            }

        }

        for (int a = 0; a <= 3; a++) {
            for (int w = 0; w <= 3; w++) {
                grille[a][w] = new Cube(grille[a][w].getValeur(), false);
            }
        }

        Score.setScore(Cube.countVal(grille));

        if (ope >= 1) {
            ramdomCube(grille, nbrspawn);
            Score.setScore(Cube.countVal(grille));

            return false;
        } else {
            return true;
        }
    }

    public static boolean checkMouv(Cube[][] grille) {
        for (int i = 0; i <= 3; i++) {
            for (int y = 0; y <= 3; y++) {
                if (i == 0 && y == 0) {
                    if (grille[i][y].getValeur() == grille[i+1][y].getValeur() || grille[i][y].getValeur() == grille[i][y+1].getValeur()) {
                        return true;
                    }
                } else if (i == 0 && y == 3) {
                    if (grille[i][y].getValeur() == grille[i+1][y].getValeur() || grille[i][y].getValeur() == grille[i][y-1].getValeur()) {
                        return true;
                    }
                } else if (i == 3 && y == 0) {
                    if (grille[i][y].getValeur() == grille[i-1][y].getValeur() || grille[i][y].getValeur() == grille[i][y+1].getValeur()) {
                        return true;
                    }
                } else if (i == 3 && y == 3) {
                    if (grille[i][y].getValeur() == grille[i-1][y].getValeur() || grille[i][y].getValeur() == grille[i][y-1].getValeur()) {
                        return true;
                    }
                } else if (i == 0 && (y == 1 || y ==2)) {
                    if (grille[i][y].getValeur() == grille[i+1][y].getValeur() || grille[i][y].getValeur() == grille[i][y+1].getValeur() || grille[i][y].getValeur() == grille[i][y-1].getValeur()) {
                        return true;
                    }
                } else if (i == 3 && (y == 1 || y ==2)) {
                    if (grille[i][y].getValeur() == grille[i-1][y].getValeur() || grille[i][y].getValeur() == grille[i][y+1].getValeur() || grille[i][y].getValeur() == grille[i][y-1].getValeur()) {
                        return true;
                    }
                } else if ((i == 1 || i ==2) && y == 0) {
                    if (grille[i][y].getValeur() == grille[i][y+1].getValeur() || grille[i][y].getValeur() == grille[i-1][y].getValeur() || grille[i][y].getValeur() == grille[i+1][y].getValeur()) {
                        return true;
                    }
                } else if ((i == 1 || i ==2) && y == 3) {
                    if (grille[i][y].getValeur() == grille[i][y-1].getValeur() || grille[i][y].getValeur() == grille[i-1][y].getValeur() || grille[i][y].getValeur() == grille[i+1][y].getValeur()) {
                        return true;
                    }
                } else {
                    if (grille[i][y].getValeur() == grille[i+1][y].getValeur() || grille[i][y].getValeur() == grille[i][y-1].getValeur() || grille[i][y].getValeur() == grille[i][y+1].getValeur() ||grille[i][y].getValeur() == grille[i-1][y].getValeur()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static int countVal(Cube[][] grille) {
        int score = 0;

        for (int i = 0; i <= 3; i++) {
            for (int y = 0; y <= 3; y++) {
                score = score + grille[i][y].getValeur();
            }
        }

        return score;
    }

    public static Cube[][] cloneGrille(Cube[][] grille) {
        Cube[][] grilleClone = new Cube[4][4];

        for (int i = 0; i <= 3; i++) {
            for (int y = 0; y <= 3; y++) {
                grilleClone[i][y] = new Cube(grille[i][y].getValeur(), false);
            }
        }

        return grilleClone;
    }
}