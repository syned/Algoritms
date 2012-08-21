using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;
using PercolationProgram;

namespace PercolationTests
{
    [TestFixture]
    public class PercolationTests
    {
        [Test]
        public void Open_cell_should_open_it()
        {
            // arrange
            var percolation = new Percolation(5);

            // act
            percolation.Open(2, 3);

            // assert
            Assert.IsTrue(percolation.IsOpen(2, 3));
        }

        [Test]
        public void Open_cell_on_the_right_bottom_corner()
        {
            // arrange
            var percolation = new Percolation(5);

            // act
            percolation.Open(5, 5);

            // assert
            Assert.IsTrue(percolation.IsOpen(5, 5));
        }

        [Test]
        public void Given_opened_cell_above_when_open_should_connect_with_cell_above()
        {
            // arrange
            var percolation = new Percolation(2);

            // act
            percolation.Open(1, 2);
            percolation.Open(2, 2);

            // assert
            Assert.IsTrue(percolation.Connected(2, 4));
        }

        [Test]
        public void Open_cell_on_the_top_shold_connect_it_with_root_above()
        {
            // arrange
            var percolation = new Percolation(2);

            // act
            percolation.Open(1, 1);            

            // assert
            Assert.IsTrue(percolation.Connected(0, 1));
        }

        [Test]
        public void Open_cell_on_the_bottom_should_connect_it_with_root_below()
        {
            // arrange
            var percolation = new Percolation(2);

            // act
            percolation.Open(2, 2);

            // assert
            Assert.IsTrue(percolation.Connected(5, 4));
        }

        [Test]
        public void Given_opened_cell_below_when_open_should_connect_with_cell_below()
        {
            // arrange
            var percolation = new Percolation(2);

            // act
            percolation.Open(2, 2);
            percolation.Open(1, 2);

            // assert
            Assert.IsTrue(percolation.Connected(2, 4));
        }

        [Test]
        public void Given_opened_cell_on_the_left_when_open_should_connect_it_with_cell_left()
        {
            // arrange
            var percolation = new Percolation(5);

            // act
            percolation.Open(2, 2);
            percolation.Open(2, 3);

            // assert
            Assert.IsTrue(percolation.Connected(7, 8));
        }

        [Test]
        public void Open_cell_on_the_left_side_of_the_grid_shouldnt_connect_it_with_previous_cell()
        {
            // arrange
            var percolation = new Percolation(5);

            // act
            percolation.Open(1, 5);
            percolation.Open(2, 1);            

            // assert
            Assert.IsFalse(percolation.Connected(5, 6));
        }

        [Test]
        public void Given_opened_cell_on_the_right_when_open_should_connect_it_with_cell_right()
        {
            // arrange
            var percolation = new Percolation(5);

            // act
            percolation.Open(2, 3);
            percolation.Open(2, 2);

            // assert
            Assert.IsTrue(percolation.Connected(7, 8));
        }

        [Test]
        public void Open_cell_on_the_right_side_of_the_grid_shouldnt_connect_it_with_next_cell()
        {
            // arrange
            var percolation = new Percolation(5);

            // act
            percolation.Open(2, 1);
            percolation.Open(1, 5);            

            // assert
            Assert.IsFalse(percolation.Connected(5, 6));
        }
    }
}
