/**
 * 
 */
package pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/*
 * Write a chess solver: a program that, given a chess piece type and a position, will
list which board positions are possible for that piece to move to. The program
should store previous pieces entered and factor those into its answer for the next
piece.
@author Amit Pandit
@version 1.0
@since   10-08-2018
 */

/* Class : ChessSetup
 * This class contains all the worker functions required to carry out different operations on chess board
 */
class ChessSetup {

 // initializing the chessBoard and all the positions of pieces
 int[][] chessBoard = new int[9][9];
 ArrayList < Integer > boundaryValuesOfChessBoard = new ArrayList < Integer > ();
 HashMap < Integer, String > hmap = new HashMap < Integer, String > ();
 HashMap < Integer, String > finalPossibleSolutions = new HashMap < Integer, String > ();
 HashMap < Integer, Integer > firstTimeTwoStepMoveForPawns = new HashMap < Integer, Integer > ();
 HashMap < Integer, Integer > trackListOfAllPawns = new HashMap < Integer, Integer > ();
 HashMap < Integer, Integer > trackListOfAllKnights = new HashMap < Integer, Integer > ();
 HashMap < Integer, Integer > trackListOfAllBishops = new HashMap < Integer, Integer > ();
 HashMap < Integer, Integer > trackListOfAllRooks = new HashMap < Integer, Integer > ();
 HashMap < Integer, Integer > trackListOfAllQueens = new HashMap < Integer, Integer > ();
 HashMap < Integer, Integer > trackListOfKing = new HashMap < Integer, Integer > ();

 /*
  * Below methods are used to initialize the hashmaps used for tracking pieces
  * @param args Unused.
  * @return: unused
  */
 public void initializePositionOfKnights() {
  trackListOfAllKnights.put(1, 12);
  trackListOfAllKnights.put(2, 17);
 }

 public void initializePositionOfKing() {
  trackListOfKing.put(1, 15);
 }
 public void initializePositionOfQueens() {
  trackListOfAllQueens.put(1, 14);
 }
 public void initializePositionOfBishops() {
  trackListOfAllBishops.put(1, 13);
  trackListOfAllBishops.put(2, 16);
 }
 public void initializePositionOfRooks() {
  trackListOfAllRooks.put(1, 11);
  trackListOfAllRooks.put(2, 18);
 }

 public void initializePositionOfPawns() {
  trackListOfAllPawns.put(1, 21);
  trackListOfAllPawns.put(2, 22);
  trackListOfAllPawns.put(3, 23);
  trackListOfAllPawns.put(4, 24);
  trackListOfAllPawns.put(5, 25);
  trackListOfAllPawns.put(6, 26);
  trackListOfAllPawns.put(7, 27);
  trackListOfAllPawns.put(8, 28);
 }
 public void initializeFirsTimePositionOfPawns() {
	 firstTimeTwoStepMoveForPawns.put(1, 21);
	 firstTimeTwoStepMoveForPawns.put(2, 22);
	 firstTimeTwoStepMoveForPawns.put(3, 23);
	 firstTimeTwoStepMoveForPawns.put(4, 24);
	 firstTimeTwoStepMoveForPawns.put(5, 25);
	 firstTimeTwoStepMoveForPawns.put(6, 26);
	 firstTimeTwoStepMoveForPawns.put(7, 27);
	 firstTimeTwoStepMoveForPawns.put(8, 28);
	 }
 
 /*
  * Below method is used to initialize the hashmap for representing column values of chess board
  * @param args : unused
  * @return: unused
  */
 public void initMappingColumnNameValuesForChessBoard() {
  hmap.put(1, "a");
  hmap.put(2, "b");
  hmap.put(3, "c");
  hmap.put(4, "d");
  hmap.put(5, "e");
  hmap.put(6, "f");
  hmap.put(7, "g");
  hmap.put(8, "h");
 }
 /*
  * Below methods are used to validate the given position of user w.r.t to the hashmaps used for tracking pieces which has 
  * current positions of all pieces
  * @param args : user given position
  * @return: boolean value
  */
 public boolean validatePositionOfRook(int positionNumber) {
  if (trackListOfAllRooks.containsValue(positionNumber)) {
   return true;
  }
  return false;
 }

 public boolean validatePositionOfPawn(int positionNumber) {
  if (trackListOfAllPawns.containsValue(positionNumber)) {
   return true;
  }
  return false;
 }
 public boolean validatePositionOfQueen(int positionNumber) {
  if (trackListOfAllQueens.containsValue(positionNumber)) {
   return true;
  }
  return false;
 }

 public boolean validatePositionOfKing(int positionNumber) {
  if (trackListOfKing.containsValue(positionNumber)) {
   return true;
  }
  return false;
 }

 public boolean validatePositionOfKnights(int positionNumber) {
  if (trackListOfAllKnights.containsValue(positionNumber)) {
   return true;
  }
  return false;
 }

 public boolean validatePositionOfBishops(int positionNumber) {
  if (trackListOfAllBishops.containsValue(positionNumber)) {
   return true;
  }
  return false;
 }

 /*
  * Below methods are used to perform integer and string conversions
  * @param args : object
  * @return: object
  */
 public Object convertPositionToNumber(Object value) {

  String str = value.toString();
  String str2 = str.substring(str.length() - 1);
  String str3 = String.valueOf(str.charAt(0));

  for (Object o: hmap.keySet()) {
   if (hmap.get(o).equals(str2)) {
    return str3.concat(o.toString());
   }
  }
  return null;
 }

 public Object convertNumberToPosition(int value) {

  String str3 = convertToString(value);
  String str1 = str3.substring(str3.length() - 1);
  String str2 = String.valueOf(str3.charAt(0));
  return str2.concat(hmap.get(convertToInt(str1)));
 }

 /*
  * Below method is used to find all possible solutions for King piece
  * @param args : user given position
  * @return: none
  */
 public void findPossiblePositionsForKing(String userPosition) {

  int currentPosition = convertToInt(userPosition);
  ArrayList < Integer > initialPossiblePositions = new ArrayList < Integer > ();

  int pos1 = currentPosition + 10;

  int pos2 = pos1 + 1;
  int pos3 = pos1 - 1;

  int pos4 = currentPosition - 10;

  int pos5 = pos4 + 1;
  int pos6 = pos4 - 1;

  int pos7 = currentPosition + 1;
  int pos8 = currentPosition - 1;

  initialPossiblePositions.add(pos1);
  initialPossiblePositions.add(pos2);
  initialPossiblePositions.add(pos3);
  initialPossiblePositions.add(pos4);
  initialPossiblePositions.add(pos5);
  initialPossiblePositions.add(pos6);
  initialPossiblePositions.add(pos7);
  initialPossiblePositions.add(pos8);

  makeReadyPossibleSolutions(initialPossiblePositions);

 }

 /*
  * Below method is used to find all possible solutions for Pawn piece
  * @param args : user given position
  * @return: none
  */
 public void findPossiblePositionsForPawns(String userPosition) {
  //	initializeBoundaryValues();
  int currentPosition = convertToInt(userPosition);
  ArrayList < Integer > initialPossiblePositions = new ArrayList < Integer > ();

  int pos1 = currentPosition + 10;
  int pos2 = pos1 + 1;
  int pos3 = pos1 - 1;

  //checking if pawn is moved for first time to take two steps
  if(firstTimeMoveForPawnWithTwoSteps(currentPosition)!=0) {
	  initialPossiblePositions.add(firstTimeMoveForPawnWithTwoSteps(currentPosition));
  }
  
  initialPossiblePositions.add(pos1);
  initialPossiblePositions.add(pos2);
  initialPossiblePositions.add(pos3);

  makeReadyPossibleSolutions(initialPossiblePositions);

 }
 
 /*
  * Below method is used to find extra position for pawn as it as option to move two moves
  * @param args : user given position
  * @return: two steps next available position
  */
 public int firstTimeMoveForPawnWithTwoSteps(int currentPosition) {
	 int twoStepPosition = 0;
	 int number1 = 0;
	 int number2 = 0;
	 LinkedList < Integer > stack = new LinkedList < Integer > ();
	 if(firstTimeTwoStepMoveForPawns.containsValue(currentPosition))
	 {
		 int number = currentPosition + 10;
		 
		   while (number > 0) {
		    stack.push(number % 10);
		    number = number / 10;
		   }
		   while (!stack.isEmpty()) {
		    number1 = stack.pop();
		    number2 = stack.pop();
		   }

		   if (chessBoard[number1][number2] == 0) {
			   twoStepPosition = currentPosition + 20;
		   }
		 
	 }
	return twoStepPosition;
 }
 /*
  * Below method is used to find all possible solutions for Knights piece
  * @param args : user given position
  * @return: none
  */
 public void findPossiblePositionsForKnights(String userPosition) {

  int currentPosition = convertToInt(userPosition);
  ArrayList < Integer > initialPossiblePositions = new ArrayList < Integer > ();

  int pos = currentPosition + 20;
  int fpos = currentPosition - 20;
  int pos1 = pos + 1;
  int pos2 = pos - 1;
  int pos3 = fpos + 1;
  int pos4 = fpos - 1;

  int leftpos = currentPosition + 2;
  int pos5 = leftpos + 10;
  int pos6 = leftpos - 10;

  int rightpos = currentPosition - 2;

  int pos7 = rightpos + 10;
  int pos8 = rightpos - 10;

  initialPossiblePositions.add(pos1);
  initialPossiblePositions.add(pos2);
  initialPossiblePositions.add(pos3);
  initialPossiblePositions.add(pos4);
  initialPossiblePositions.add(pos5);
  initialPossiblePositions.add(pos6);
  initialPossiblePositions.add(pos7);
  initialPossiblePositions.add(pos8);

  makeReadyPossibleSolutions(initialPossiblePositions);

 }

 /*
  * Below method is used to find all possible solutions for Queen piece
  * @param args : user given position
  * @return: none
  */
 public void findPossiblePositionsForQueen(String userPosition) {
  int currentPosition = convertToInt(userPosition);

  ArrayList < Integer > initialPossiblePositions = new ArrayList < Integer > ();

  //for 11
  initialPossiblePositions = allDirectionsPossibleSolutions(currentPosition, 11, 0, initialPossiblePositions);
  //for 9
  initialPossiblePositions = allDirectionsPossibleSolutions(currentPosition, 9, 0, initialPossiblePositions);
  //for -9
  initialPossiblePositions = allDirectionsPossibleSolutions(currentPosition, 9, 1, initialPossiblePositions);
  //for -11
  initialPossiblePositions = allDirectionsPossibleSolutions(currentPosition, 11, 1, initialPossiblePositions);

  //for 11
  initialPossiblePositions = allDirectionsPossibleSolutions(currentPosition, 10, 0, initialPossiblePositions);
  //for 9
  initialPossiblePositions = allDirectionsPossibleSolutions(currentPosition, 1, 0, initialPossiblePositions);
  //for -9
  initialPossiblePositions = allDirectionsPossibleSolutions(currentPosition, 10, 1, initialPossiblePositions);
  //for -11
  initialPossiblePositions = allDirectionsPossibleSolutions(currentPosition, 1, 1, initialPossiblePositions);

  for (int i = 0; i < initialPossiblePositions.size(); i++) {

   finalPossibleSolutions.put(i, convertNumberToPosition(initialPossiblePositions.get(i)).toString());
  }
 }

 /*
  * Below method is used to find all possible solutions for Bishop piece
  * @param args : user given position
  * @return: none
  */
 public void findPossiblePositionsForBishop(String userPosition) {
  int currentPosition = convertToInt(userPosition);

  ArrayList < Integer > initialPossiblePositions = new ArrayList < Integer > ();

  //for 11
  initialPossiblePositions = allDirectionsPossibleSolutions(currentPosition, 11, 0, initialPossiblePositions);
  //for 9
  initialPossiblePositions = allDirectionsPossibleSolutions(currentPosition, 9, 0, initialPossiblePositions);
  //for -9
  initialPossiblePositions = allDirectionsPossibleSolutions(currentPosition, 9, 1, initialPossiblePositions);
  //for -11
  initialPossiblePositions = allDirectionsPossibleSolutions(currentPosition, 11, 1, initialPossiblePositions);


  for (int i = 0; i < initialPossiblePositions.size(); i++) {

   finalPossibleSolutions.put(i, convertNumberToPosition(initialPossiblePositions.get(i)).toString());
  }


 }
 /*
  * Below method is used to find all possible solutions for Rook piece
  * @param args : user given position
  * @return: none
  */
 public void findPossiblePositionsForRook(String userPosition) {
  int currentPosition = convertToInt(userPosition);
  ArrayList < Integer > initialPossiblePositions = new ArrayList < Integer > ();

  //for 11
  initialPossiblePositions = allDirectionsPossibleSolutions(currentPosition, 10, 0, initialPossiblePositions);
  //for 9
  initialPossiblePositions = allDirectionsPossibleSolutions(currentPosition, 1, 0, initialPossiblePositions);
  //for -9
  initialPossiblePositions = allDirectionsPossibleSolutions(currentPosition, 10, 1, initialPossiblePositions);
  //for -11
  initialPossiblePositions = allDirectionsPossibleSolutions(currentPosition, 1, 1, initialPossiblePositions);


  for (int i = 0; i < initialPossiblePositions.size(); i++) {

   finalPossibleSolutions.put(i, convertNumberToPosition(initialPossiblePositions.get(i)).toString());
  }

 }

 /*
  * Below method is used to find all possible solutions for all four directions where a piece can traverse on the chessBoard
  * @param args : currentPosition of piece, multiple- which direction to move, initial possible solutions
  * @return: list of solutions
  */
 public ArrayList < Integer > allDirectionsPossibleSolutions(int currentPosition, int multiplier, int reducer, ArrayList < Integer > initialPossiblePositions) {

  int newPosition = 0;

  if (reducer == 0) {
   newPosition = currentPosition + multiplier;
  } else {
   newPosition = currentPosition - multiplier;
  }
  for (int i = currentPosition; i < 89 || i > 10; i++) {

   if (invalidBoundaryChecksForBishop(newPosition)) {

    break;
   } else {
    initialPossiblePositions.add(newPosition);
    if (reducer == 0) {
     newPosition = newPosition + multiplier;
    } else {
     newPosition = newPosition - multiplier;
    }
   }

  }

  return initialPossiblePositions;

 }

 /*
  * Below method is used to validate the obtained possible solution against the boundary positions
  * @param args : obtained current position
  * @return: boolean
  */
 public boolean invalidBoundaryChecksForBishop(int newPosition) {

  if (newPosition < 10 || newPosition > 88) {
   return true;

  } else if (reachedBoundary(newPosition)) {
   return true;

  } else if (!nearestPathFirstLocation(newPosition)) {
   return true;
  }
  return false;
 }

 /*
  * Below method is used to find the nearest possible position that a rook or queen can move without any hurdle
  * @param args : current position
  * @return: boolean value
  */
 public boolean nearestPathFirstLocation(int currentPosition) {

  LinkedList < Integer > stack = new LinkedList < Integer > ();
  int number1 = 0;
  int number2 = 0;
  int number = currentPosition;

  while (number > 0) {
   stack.push(number % 10);
   number = number / 10;
  }
  while (!stack.isEmpty()) {
   number1 = stack.pop();
   //System.out.println("number 1 is"+number1);
   number2 = stack.pop();
   //System.out.println("numer 2 is:"+number2);
  }

  if (chessBoard[number1][number2] == 0) {
   return true;
  }
  return false;
 }

 /*
  * Below method is used to validate all obtained positions against the boundary positions
  * @param args : given position
  * @return: boolean value
  */
 public boolean reachedBoundary(int givenPosition) {
  if (boundaryValuesOfChessBoard.contains(givenPosition)) {
   return true;
  }
  return false;
 }

 /*
  * Below method is used to filter the initial obtained positions with boundary positions invalid checks
  * @param args : initial list of all possible solutions
  * @return: filtered list of all possible solutions
  */
 public void makeReadyPossibleSolutions(ArrayList < Integer > initialPossiblePositions) {
  //validating with boundary values and filtering out
  ArrayList < Integer > filterdPositions = validateBoundaryPositions(initialPossiblePositions);
  filterdPositions = validateAgainstOccupiedPositions(filterdPositions);

  if (filterdPositions.isEmpty()) {
   System.out.println("Sorry !! No posssible Solutions. Make sure you have selected correct position or try with other piece");
  } else {
   finalPossibleSolutions.clear();
   for (int i = 0; i < filterdPositions.size(); i++) {

    finalPossibleSolutions.put(i, convertNumberToPosition(filterdPositions.get(i)).toString());
   }
  }
 }

 /*
  * Below method is used to validate all obtained positions against the boundary positions
  * @param args : given position
  * @return: boolean value
  */
 public ArrayList < Integer > validateBoundaryPositions(ArrayList < Integer > possibleSolutionsToFilter) {
  ArrayList < Integer > positionsWithBoundaryValidated = new ArrayList < Integer > ();

  for (int i = 0; i < possibleSolutionsToFilter.size(); i++) {
   if (!boundaryValuesOfChessBoard.contains(possibleSolutionsToFilter.get(i))) {
    if (possibleSolutionsToFilter.get(i) > 10 && possibleSolutionsToFilter.get(i) < 88) {
     positionsWithBoundaryValidated.add(possibleSolutionsToFilter.get(i));
    }
   }
  }
  return positionsWithBoundaryValidated;
 }

 /*
  * Below methods are used to update the chess board with given move changes for a particular piece
  * @param args : current position,selected position
  * @return: unused
  */

 public void updateChessBoardWithUserMoveOfPawn(String currentPosition, String selectedPosition) {


  int currentNo = convertToInt(convertPositionToNumber(currentPosition).toString());
  int selectedNo = convertToInt(convertPositionToNumber(selectedPosition).toString());

  updatePawnTrackList(currentNo, selectedNo);

  updateChessPosition(currentNo, 0);
  updateChessPosition(selectedNo, 1);
 }
 public void updateChessBoardWithUserMoveOfBishops(String currentPosition, String selectedPosition) {


  int currentNo = convertToInt(convertPositionToNumber(currentPosition).toString());
  int selectedNo = convertToInt(convertPositionToNumber(selectedPosition).toString());

  updateBishopTrackList(currentNo, selectedNo);

  updateChessPosition(currentNo, 0);
  updateChessPosition(selectedNo, 1);
 }

 public void updateChessBoardWithUserMoveOfRooks(String currentPosition, String selectedPosition) {


  int currentNo = convertToInt(convertPositionToNumber(currentPosition).toString());
  int selectedNo = convertToInt(convertPositionToNumber(selectedPosition).toString());

  updateRookTrackList(currentNo, selectedNo);

  updateChessPosition(currentNo, 0);
  updateChessPosition(selectedNo, 1);
 }

 public void updateChessBoardWithUserMoveOfQueens(String currentPosition, String selectedPosition) {


  int currentNo = convertToInt(convertPositionToNumber(currentPosition).toString());
  int selectedNo = convertToInt(convertPositionToNumber(selectedPosition).toString());

  updateQueenTrackList(currentNo, selectedNo);

  updateChessPosition(currentNo, 0);
  updateChessPosition(selectedNo, 1);
 }

 public void updateChessBoardWithUserMoveOfKnights(String currentPosition, String selectedPosition) {


  int currentNo = convertToInt(convertPositionToNumber(currentPosition).toString());
  int selectedNo = convertToInt(convertPositionToNumber(selectedPosition).toString());

  updateKnightTrackList(currentNo, selectedNo);

  updateChessPosition(currentNo, 0);
  updateChessPosition(selectedNo, 1);
 }

 public void updateChessBoardWithUserMoveOfKing(String currentPosition, String selectedPosition) {


  int currentNo = convertToInt(convertPositionToNumber(currentPosition).toString());
  int selectedNo = convertToInt(convertPositionToNumber(selectedPosition).toString());

  updateKingTrackList(currentNo, selectedNo);

  updateChessPosition(currentNo, 0);
  updateChessPosition(selectedNo, 1);
 }

 /*
  * Below methods are used to update the track lists of particular piece with the associated move changes
  * @param args : current position,selected position
  * @return: unused
  */
 public void updateRookTrackList(int currentPositionNumber, int selectedPositionNumber) {
  //trackListOfAllPawns.containsValue(positionNumber);

  for (Object o: trackListOfAllRooks.keySet()) {
   if (trackListOfAllRooks.get(o).equals(currentPositionNumber)) {
    trackListOfAllRooks.remove(convertToInt(o.toString()));
    trackListOfAllRooks.put(convertToInt(o.toString()), selectedPositionNumber);
    break;
   }
  }

 }

 public void updateQueenTrackList(int currentPositionNumber, int selectedPositionNumber) {
  for (Object o: trackListOfAllQueens.keySet()) {
   if (trackListOfAllQueens.get(o).equals(currentPositionNumber)) {
    trackListOfAllQueens.remove(convertToInt(o.toString()));
    trackListOfAllQueens.put(convertToInt(o.toString()), selectedPositionNumber);
    break;
   }
  }

 }
 public void updatePawnTrackList(int currentPositionNumber, int selectedPositionNumber) {
  for (Object o: trackListOfAllPawns.keySet()) {
   if (trackListOfAllPawns.get(o).equals(currentPositionNumber)) {
    trackListOfAllPawns.remove(convertToInt(o.toString()));
    trackListOfAllPawns.put(convertToInt(o.toString()), selectedPositionNumber);
    break;
   }
  }
 }

 public void updateKingTrackList(int currentPositionNumber, int selectedPositionNumber) {
  for (Object o: trackListOfKing.keySet()) {
   if (trackListOfKing.get(o).equals(currentPositionNumber)) {
    trackListOfKing.remove(convertToInt(o.toString()));
    trackListOfKing.put(convertToInt(o.toString()), selectedPositionNumber);
    break;
   }
  }

 }

 public void updateKnightTrackList(int currentPositionNumber, int selectedPositionNumber) {
  for (Object o: trackListOfAllKnights.keySet()) {
   if (trackListOfAllKnights.get(o).equals(currentPositionNumber)) {
    trackListOfAllKnights.remove(convertToInt(o.toString()));
    trackListOfAllKnights.put(convertToInt(o.toString()), selectedPositionNumber);
    break;
   }
  }
 }
 public void updateBishopTrackList(int currentPositionNumber, int selectedPositionNumber) {

  for (Object o: trackListOfAllBishops.keySet()) {
   if (trackListOfAllBishops.get(o).equals(currentPositionNumber)) {
    trackListOfAllBishops.remove(convertToInt(o.toString()));
    trackListOfAllBishops.put(convertToInt(o.toString()), selectedPositionNumber);
    break;
   }
  }
 }
 public void updateChessPosition(int position, int value) {
  LinkedList < Integer > stack = new LinkedList < Integer > ();
  int number1 = 0;
  int number2 = 0;

  int number = position;
  while (number > 0) {
   stack.push(number % 10);
   number = number / 10;
  }
  while (!stack.isEmpty()) {
   number1 = stack.pop();
   number2 = stack.pop();
  }
  chessBoard[number1][number2] = value;
 }

 /*
  * Below method is used validate the obtained possible solutions against the already occupied positions on the chess board
  * @param args : list of filtered positions
  * @return: filtered list of positions
  */
 public ArrayList < Integer > validateAgainstOccupiedPositions(ArrayList < Integer > filterdPositions) {

  ArrayList < Integer > positionsWithOccupancyValidated = new ArrayList < Integer > ();
  LinkedList < Integer > stack = new LinkedList < Integer > ();
  int number1 = 0;
  int number2 = 0;
  for (int i = 0; i < filterdPositions.size(); i++) {
   int number = filterdPositions.get(i);
   while (number > 0) {
    stack.push(number % 10);
    number = number / 10;
   }
   while (!stack.isEmpty()) {
    number1 = stack.pop();
    number2 = stack.pop();
   }

   if (chessBoard[number1][number2] == 0) {
    positionsWithOccupancyValidated.add(filterdPositions.get(i));
   }
  } //for
  return positionsWithOccupancyValidated;
 }

 /*
  * Below method is used to initialize the boundary values for chess board
  * @param args : unused
  * @return: unused
  */
 public void initializeBoundaryValues() {

  int val = 10;
  for (int i = 0; i < 8; i++) {
   boundaryValuesOfChessBoard.add(val);
   val = val + 10;
  }

  val = 91;
  for (int i = 0; i <= 9; i++) {
   boundaryValuesOfChessBoard.add(val);
   val = val + 1;
  }
  val = 19;
  for (int i = 0; i < 8; i++) {
   boundaryValuesOfChessBoard.add(val);
   val = val + 10;
  }
 }


 public int convertToInt(String str) {
  int result = 0;
	try {
  result = Integer.parseInt(str);
	}
	catch (NumberFormatException e) {
        System.out.println("WARNING : Invalid Choice Selected. Default choice taken now. Please select Number Choice correctly again!");
    }
  return result;

 }
 public String convertToString(int value) {

  String result = Integer.toString(value);
  return result;

 }
 /*
  * Below method is used to initialize the chess board
  * @param args : unused
  * @return: unused
  */
 public void initChessBoard() {
  System.out.println("");
  for (int i = 1; i < 3; i++) {
   for (int j = 1; j < chessBoard[i].length; j++) {
    chessBoard[i][j] = 1;
   }
  }
  for (int i = 7; i < chessBoard.length; i++) {
   for (int j = 0; j < chessBoard[i].length; j++) {
    chessBoard[i][j] = 1;
   }
  }
 }
 /*
  * Below method is used to print the chess board
  * @param args : unused
  * @return: unused
  */
 public void printBoardStatus() {
  for (int i = 1; i < chessBoard.length; i++) {
   for (int j = 1; j < chessBoard[i].length; j++) {
    System.out.print(chessBoard[i][j] + " ");
   }
   System.out.println("");
  }  
 }

 public void printBoard() {
  System.out.println("**************CHESS BOARD*******************\n");
  initMappingColumnNameValuesForChessBoard();
  for (int i = 1; i < chessBoard.length; i++) {
   for (int j = 1; j < chessBoard[i].length; j++) {
    System.out.print(i + "" + hmap.get(j) + " ");
   }
   System.out.println("");
  }
  System.out.println("\n*********************************************");
  System.out.println("Below rules/assumptions");
  System.out.println(">>  Only one player functionality has been implemented");
  System.out.println(">>  Player will be playing from top view side i.e 1a - 1h");
  System.out.println(">>  Pawns have a option of moving ahead 2 moves very first time from inital position");

 }

}

/* Class : Chess
 * This is main class performing all functions related to given user.
 */
public class Chess {
 public static void main(String[] args) {
  ChessSetup cs = new ChessSetup();

  System.out.println("Loading....");
  cs.initChessBoard();
  cs.printBoard();
  cs.initializeBoundaryValues();
  cs.initializePositionOfKing();
  cs.initializePositionOfPawns();
  cs.initializeFirsTimePositionOfPawns();
  cs.initializePositionOfKnights();
  cs.initializePositionOfBishops();
  cs.initializePositionOfRooks();
  cs.initializePositionOfQueens();

  System.out.println("\n\n>> Chess board initialization completed. Lets play !!");

  Scanner scan = new Scanner(System.in);
  char ch;
  do {
   System.out.println("\nChess operations\n");
   System.out.println("1.Rook");
   System.out.println("2.Knight");
   System.out.println("3.Bishop");
   System.out.println("4.Queen");
   System.out.println("5.King");
   System.out.println("6.Pawn");
   System.out.println("7.Exit");
   System.out.println("Select the piece operation [1-7] : ");
   int choice = scan.nextInt();
   switch (choice) {
    case 1:
    	
     // Rook 
    	
     String givenRookPosition = null;
     int rookFlag = 0;

     System.out.println("Enter the current position of Rook : ");
     BufferedReader givenRookPositionObject = new BufferedReader(new InputStreamReader(System.in));
     try {
      givenRookPosition = givenRookPositionObject.readLine();
      
      // validating the user given position with current position of rook on chess board
      if (!cs.validatePositionOfRook(cs.convertToInt(cs.convertPositionToNumber(givenRookPosition).toString()))) {
       System.out.println("There is no Rook at given position.Please provide correct position");
       rookFlag = 1;
      } else {
    	  cs.finalPossibleSolutions.clear();
    	  // find possible solutions for piece
       cs.findPossiblePositionsForRook(cs.convertPositionToNumber(givenRookPosition).toString());
       if (cs.finalPossibleSolutions.isEmpty()) {
        System.out.println("Sorry ! No possible solutions. Try with other piece or move");
        rookFlag = 1;
       } else {
    	   
        for (int key: cs.finalPossibleSolutions.keySet()) {
         System.out.println("Possible solution " + key + " : " + cs.finalPossibleSolutions.get(key));
        }
       }
      }
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }catch (InputMismatchException e) {
         System.out.println("**Please enter correct Integer value");
         rookFlag = 1;
     }catch (NullPointerException e) {
         System.out.println("**Please enter correct input value");
         rookFlag = 1;
     }

     if (rookFlag == 0) {
      System.out.println("Select the position number you want the rook to move : ");
      BufferedReader obj1 = new BufferedReader(new InputStreamReader(System.in));
      String rm2 = null;
      try {
       rm2 = obj1.readLine();
      }catch (NullPointerException e) {
          System.out.println("**Please enter correct input value");
      }catch (NumberFormatException e) {
          System.out.println("**Please enter correct input value");
      } catch (IOException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
      }
      
      //update the chess board with move changes
      String currentPost = givenRookPosition;
      String selectedPos = cs.finalPossibleSolutions.get(cs.convertToInt(rm2)).toString();
      cs.updateChessBoardWithUserMoveOfRooks(currentPost, selectedPos);

      System.out.println(">> Finally selected position : " + cs.finalPossibleSolutions.get(cs.convertToInt(rm2)));
      cs.printBoardStatus();
     }
     
     // Rook ends
     break;
    case 2:
    	
    // Knight
    	
     String givenKnightPosition = null;
     int KnightFlag = 0;

     System.out.println("Enter the current position of Knight : ");
     BufferedReader givenKnightPositionObject = new BufferedReader(new InputStreamReader(System.in));
     try {
      givenKnightPosition = givenKnightPositionObject.readLine();
          
      // validating the user given position with current position of Knight on chess board
      
      if (!cs.validatePositionOfKnights(cs.convertToInt(cs.convertPositionToNumber(givenKnightPosition).toString()))) {
       System.out.println("There is no pawn at given position.Please provide correct position");
       KnightFlag = 1;
      } else {
    	  cs.finalPossibleSolutions.clear();
    	// find possible solutions for piece
       cs.findPossiblePositionsForKnights(cs.convertPositionToNumber(givenKnightPosition).toString());
       if (cs.finalPossibleSolutions.isEmpty()) {
           System.out.println("Sorry ! No possible solutions. Try with other piece or move");
           KnightFlag = 1;
          } 
       else {
       for (int key: cs.finalPossibleSolutions.keySet()) {
        System.out.println("Possible solution " + key + " : " + cs.finalPossibleSolutions.get(key));
       }
       }
      }
     }catch (NullPointerException e) {
         System.out.println("**Please enter correct input value");
         KnightFlag = 1;
     }catch (NumberFormatException e) {
         System.out.println("**Please enter correct input value");
         KnightFlag = 1;
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }

     if (KnightFlag == 0) {
      System.out.println("Select the position number you want the Knight to move : ");
      BufferedReader selectedKnightPositionObject = new BufferedReader(new InputStreamReader(System.in));
      String selectedPosition = null;
      try {
       selectedPosition = selectedKnightPositionObject.readLine();
      }catch (NullPointerException e) {
          System.out.println("**Please enter correct input value");
      }catch (NumberFormatException e) {
          System.out.println("**Please enter correct input value");
      } catch (IOException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
      }
      
    //update the chess board with move changes
      String currentPost = givenKnightPosition;
      String selectedPos = cs.finalPossibleSolutions.get(cs.convertToInt(selectedPosition)).toString();
      cs.updateChessBoardWithUserMoveOfKnights(currentPost, selectedPos);

      System.out.println(">> Finally selected position : " + cs.finalPossibleSolutions.get(cs.convertToInt(selectedPosition)));
      cs.printBoardStatus();
     }
     
     // Knight ends
     break;
    case 3:
    
    //Bishop
    	
     String givenBishopPosition = null;
     int BishopFlag = 0;

     System.out.println("Enter the current position of Bishop : ");
     BufferedReader givenBishopPositionObject = new BufferedReader(new InputStreamReader(System.in));
     try {
      givenBishopPosition = givenBishopPositionObject.readLine();
      
   // validating the user given position with current position of Bishop on chess board
      
      if (!cs.validatePositionOfBishops(cs.convertToInt(cs.convertPositionToNumber(givenBishopPosition).toString()))) {
       System.out.println("There is no Bishop at given position.Please provide correct position");
       BishopFlag = 1;
      } else {
    	  cs.finalPossibleSolutions.clear();
   // find possible solutions for piece
       cs.findPossiblePositionsForBishop(cs.convertPositionToNumber(givenBishopPosition).toString());
       if (cs.finalPossibleSolutions.isEmpty()) {
           System.out.println("Sorry ! No possible solutions. Try with other piece or move");
           BishopFlag = 1;
          } 
       else {
       for (int key: cs.finalPossibleSolutions.keySet()) {
        System.out.println("Possible solution " + key + " : " + cs.finalPossibleSolutions.get(key));
       }
       }
      }
     }catch (NullPointerException e) {
         System.out.println("**Please enter correct input value");
         BishopFlag = 1;
     }catch (NumberFormatException e) {
         System.out.println("**Please enter correct input value");
         BishopFlag = 1;
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }

     if (BishopFlag == 0) {
      System.out.println("Select the position number you want the bishop to move");
      BufferedReader obj1 = new BufferedReader(new InputStreamReader(System.in));
      String rm2 = null;
      try {
       rm2 = obj1.readLine();
      }catch (NullPointerException e) {
          System.out.println("**Please enter correct input value");
      }catch (NumberFormatException e) {
          System.out.println("**Please enter correct input value");
      } catch (IOException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
      }
      
      //update the chess board with move changes 
      String currentPost = givenBishopPosition;
      String selectedPos = cs.finalPossibleSolutions.get(cs.convertToInt(rm2)).toString();
      cs.updateChessBoardWithUserMoveOfBishops(currentPost, selectedPos);

      System.out.println(">> Finally selected position : " + cs.finalPossibleSolutions.get(cs.convertToInt(rm2)));
      cs.printBoardStatus();
     }
     break;
     
     //Bishop ends
    case 4:

     // Queen 
    	
     String givenQueenPosition = null;
     int queenFlag = 0;

     System.out.println("Enter the current position of Queen");
     BufferedReader givenQueenPositionObject = new BufferedReader(new InputStreamReader(System.in));
     try {
      givenQueenPosition = givenQueenPositionObject.readLine();
   // validating the user given position with current position of Queen on chess board
      if (!cs.validatePositionOfQueen(cs.convertToInt(cs.convertPositionToNumber(givenQueenPosition).toString()))) {
       System.out.println("There is no Queen at given position.Please provide correct position");
       queenFlag = 1;
      } else {
    	  cs.finalPossibleSolutions.clear();
   // find possible solutions for piece
       cs.findPossiblePositionsForQueen(cs.convertPositionToNumber(givenQueenPosition).toString());
       if (cs.finalPossibleSolutions.isEmpty()) {
        System.out.println("Sorry ! No possible solutions. Try with other piece or move");
        queenFlag = 1;
       } else {
        for (int key: cs.finalPossibleSolutions.keySet()) {
         System.out.println("Possible solution " + key + " : " + cs.finalPossibleSolutions.get(key));
        }
       }
      }
     }catch (NullPointerException e) {
         System.out.println("**Please enter correct input value");
         queenFlag = 1;
     }catch (NumberFormatException e) {
         System.out.println("**Please enter correct input value");
         queenFlag = 1;
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }

     if (queenFlag == 0) {
      System.out.println("Select the position number you want the queen to move");
      BufferedReader obj1 = new BufferedReader(new InputStreamReader(System.in));
      String rm2 = null;
      try {
       rm2 = obj1.readLine();
      }catch (NullPointerException e) {
          System.out.println("**Please enter correct input value");
      }catch (NumberFormatException e) {
          System.out.println("**Please enter correct input value");
      } catch (IOException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
      }
      
    //update the chess board with move changes
      String currentPost = givenQueenPosition;
      String selectedPos = cs.finalPossibleSolutions.get(cs.convertToInt(rm2)).toString();
      cs.updateChessBoardWithUserMoveOfQueens(currentPost, selectedPos);

      System.out.println(">> Finally selected position : " + cs.finalPossibleSolutions.get(cs.convertToInt(rm2)));
      cs.printBoardStatus();
     }
     
     //Queen ends
     
     break;
    case 5:
    	
     // King 
    	
     int Kingflag = 0;
     System.out.println("Enter the current position of King");
     String givenPositionForKing = null;
     BufferedReader givenPositionForKingObj = new BufferedReader(new InputStreamReader(System.in));
     try {
      givenPositionForKing = givenPositionForKingObj.readLine();      
   // validating the user given position with current position of King on chess board
       
      if (!cs.validatePositionOfKing(cs.convertToInt(cs.convertPositionToNumber(givenPositionForKing).toString()))) {
       System.out.println("There is no pawn at given position.Please provide correct position");
       Kingflag = 1;
      } else {
    	  cs.finalPossibleSolutions.clear();
    	// find possible solutions for piece
    	  cs.findPossiblePositionsForKing(cs.convertPositionToNumber(givenPositionForKing).toString());
    	  if (cs.finalPossibleSolutions.isEmpty()) {
    	        System.out.println("Sorry ! No possible solutions. Try with other piece or move");
    	        Kingflag = 1;
    	       } 
    	  else {
       for (int key: cs.finalPossibleSolutions.keySet()) {
        System.out.println("Possible solution " + key + " : " + cs.finalPossibleSolutions.get(key));
       }
    	  }
      }
     }catch (NullPointerException e) {
         System.out.println("**Please enter correct input value");
         Kingflag = 1;
     }catch (NumberFormatException e) {
         System.out.println("**Please enter correct input value");
         Kingflag = 1;
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }

     if (Kingflag == 0) {
      System.out.println("Select the position number you want the King to move");
      BufferedReader obj1 = new BufferedReader(new InputStreamReader(System.in));
      String rm2 = null;
      try {
       rm2 = obj1.readLine();
      }catch (NullPointerException e) {
          System.out.println("**Please enter correct input value");
      }catch (NumberFormatException e) {
          System.out.println("**Please enter correct input value");
      } catch (IOException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
      }
      
      //update the chess board with move changes
      
      String currentPost = givenPositionForKing;
      String selectedPos = cs.finalPossibleSolutions.get(cs.convertToInt(rm2)).toString();
      cs.updateChessBoardWithUserMoveOfKing(currentPost, selectedPos);

      System.out.println(">> Finally selected position : " + cs.finalPossibleSolutions.get(cs.convertToInt(rm2)));
      cs.printBoardStatus();
     }
     break;
     
    // King ends
     
    case 6:
    	
    //Pawn
    	
     int Pawnflag = 0;
     System.out.println("Enter the current position of Pawn");
     String rm = null;
     BufferedReader obj = new BufferedReader(new InputStreamReader(System.in));
     try {
      rm = obj.readLine();
   // validating the user given position with current position of Pawn on chess board
      if (!cs.validatePositionOfPawn(cs.convertToInt(cs.convertPositionToNumber(rm).toString()))) {
       System.out.println("There is no pawn at given position.Please provide correct position");
       Pawnflag = 1;
      } else {
    	// find possible solutions for piece
    	  cs.finalPossibleSolutions.clear();
    	    cs.findPossiblePositionsForPawns(cs.convertPositionToNumber(rm).toString());
    	    if (cs.finalPossibleSolutions.isEmpty()) {
    	        System.out.println("Sorry ! No possible solutions. Try with other piece or move");
    	        Pawnflag = 1;
    	       } 
    	    else {
       for (int key: cs.finalPossibleSolutions.keySet()) {
        System.out.println("Possible solution " + key + " : " + cs.finalPossibleSolutions.get(key));
       }
    	    }
      }
     }catch (NullPointerException e) {
         System.out.println("**Please enter correct input value");
         Pawnflag = 1;
     }catch (NumberFormatException e) {
         System.out.println("**Please enter correct input value");
         Pawnflag = 1;
     } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
     }
     
     if (Pawnflag == 0) {
      System.out.println("Select the position number you want the pawn to move");
      BufferedReader obj1 = new BufferedReader(new InputStreamReader(System.in));
      String rm2 = null;
      try {
       rm2 = obj1.readLine();
      }catch (NullPointerException e) {
          System.out.println("**Please enter correct input value");
      }catch (NumberFormatException e) {
          System.out.println("**Please enter correct input value");
      } catch (IOException e) {
       // TODO Auto-generated catch block
       e.printStackTrace();
      }
      
      //update the chess board with move changes
      
      String currentPost = rm;
      String selectedPos = cs.finalPossibleSolutions.get(cs.convertToInt(rm2)).toString();
      cs.updateChessBoardWithUserMoveOfPawn(currentPost, selectedPos);

      System.out.println(">> Finally selected position : " + cs.finalPossibleSolutions.get(cs.convertToInt(rm2)));
      cs.printBoardStatus();
     }
     break;
    default:
     System.out.println("Wrong Entry \n ");
     break;
   }

   System.out.println("\nDo you want to continue (Type y or n) \n");
   ch = scan.next().charAt(0);
  } while (ch == 'Y' || ch == 'y');
 }
}