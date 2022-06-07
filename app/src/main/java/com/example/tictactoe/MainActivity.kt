package com.example.tictactoe
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    //1=green  0 =red
    private var activePlayer = 1
    private var gameIsActive = true
    private var count = 0
    private var gameState = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)
    private var winningPositions = arrayOf(
        intArrayOf(0, 1, 2),
        intArrayOf(3, 4, 5),
        intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6),
        intArrayOf(1, 4, 7),
        intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8),
        intArrayOf(2, 4, 6)
    )

    fun dropIn(view: View) {
        val counter = view as ImageView
        val txt = findViewById<TextView>(R.id.winner1)
        val layout = findViewById<LinearLayout>(R.id.winner)
        val tappedcounter = counter.tag.toString().toInt()
        if (gameState[tappedcounter] == 2 && gameIsActive) {
            if (activePlayer == 1) {
                counter.setImageResource(R.drawable.xicon)
                activePlayer = 0
                count++
                gameState[tappedcounter] = 1
            } else {
                counter.setImageResource(R.drawable.oicon)
                activePlayer = 1
                count++
                gameState[tappedcounter] = 0
            }
            for (winningposition in winningPositions) {
                if (gameState[winningposition[0]] == gameState[winningposition[1]] && gameState[winningposition[1]] == gameState[winningposition[2]] && gameState[winningposition[0]] != 2
                ) {
                    if (gameState[winningposition[0]] == 0) txt.text =
                        getString(R.string.winner_o) else if (gameState[winningposition[0]] == 1
                    ) txt.text = getString(R.string.winner_x)
                    layout.visibility = View.VISIBLE
                    gameIsActive = false
                }
            }
        }
        if (gameIsActive && count == 9) {
            txt.text = getString(R.string.winner_draw)
            layout.visibility = View.VISIBLE
            gameIsActive = false
        }
    }

    fun playAgainButton(view: View)
    {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.apply {
            setTitle("Alert")
            setMessage("Do you want to play again?\nAll the previous progress will be lost.")
            setPositiveButton(R.string.play_again) { _, _ ->
                playAgain()
            }
            setNegativeButton("No") { _, _ ->}
        }.create().show()
    }

    private fun playAgain() {
        activePlayer = 1
        gameIsActive = true
        count = 0
        val linearLayout = findViewById<LinearLayout>(R.id.winner)
        val gridLayout =
            findViewById<GridLayout>(R.id.gridLayout)
        for (i in gameState.indices) {
            gameState[i] = 2
        }
        linearLayout.visibility = View.INVISIBLE
        for (i in 0 until gridLayout.childCount) {
            (gridLayout.getChildAt(i) as ImageView).setImageResource(0)
        }
    }

    fun exit(view: View?)
    {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.apply {
            setTitle("Alert")
            setMessage("Do you really want to exit?")
            setPositiveButton(R.string.exit) { _, _ ->
                finish()
            }
            setNegativeButton(R.string.no) { _, _ ->}
        }.create().show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

