package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {
    private ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString() {
        return "P";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position p = new Position(0, 0);

        //Peao branco
        if (getColor() == Color.WHITE){
            //Movimento
            p.setValues(position.getRow()-1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().isThereAPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
                if (getMoveCount() == 0){
                    p.setValues(position.getRow()-2, position.getColumn());
                    if (getBoard().positionExists(p) && !getBoard().isThereAPiece(p)){
                        mat[p.getRow()][p.getColumn()] = true;
                    }
                }
            }
            //Tomar na diagonal
            p.setValues(position.getRow()-1, position.getColumn()-1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()-1, position.getColumn()+1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            //Movimento en passant brancas
            if (position.getRow() == 3){
                Position left = new Position(position.getRow(), position.getColumn()-1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && 
                    getBoard().piece(left) == chessMatch.getEnPassantVulnerable()){
                    mat[left.getRow()-1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn()+1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(right) && 
                    getBoard().piece(right) == chessMatch.getEnPassantVulnerable()){
                    mat[right.getRow()-1][right.getColumn()] = true;
                }
            }
        //Peao preto
        }else{
            //Movimento
            p.setValues(position.getRow()+1, position.getColumn());
            if (getBoard().positionExists(p) && !getBoard().isThereAPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
                if (getMoveCount() == 0){
                    p.setValues(position.getRow()+2, position.getColumn());
                    if (getBoard().positionExists(p) && !getBoard().isThereAPiece(p)){
                        mat[p.getRow()][p.getColumn()] = true;
                    }
                }
            }
            //Tomar na diagonal
            p.setValues(position.getRow()+1, position.getColumn()-1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            p.setValues(position.getRow()+1, position.getColumn()+1);
            if (getBoard().positionExists(p) && isThereOpponentPiece(p)){
                mat[p.getRow()][p.getColumn()] = true;
            }
            //Movimento en passant pretas
            if (position.getRow() == 4){
                Position left = new Position(position.getRow(), position.getColumn()-1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && 
                    getBoard().piece(left) == chessMatch.getEnPassantVulnerable()){
                    mat[left.getRow()+1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn()+1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(right) && 
                    getBoard().piece(right) == chessMatch.getEnPassantVulnerable()){
                    mat[right.getRow()+1][right.getColumn()] = true;
                }
            }
        }

        return mat;
    }
}